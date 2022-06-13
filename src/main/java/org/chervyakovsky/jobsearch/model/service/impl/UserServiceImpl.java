package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.CredentialDao;
import org.chervyakovsky.jobsearch.model.dao.UserDao;
import org.chervyakovsky.jobsearch.model.dao.impl.CredentialDaoImpl;
import org.chervyakovsky.jobsearch.model.dao.impl.UserDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.CredentialMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.util.PasswordEncryptor;
import org.chervyakovsky.jobsearch.util.mail.Mail;
import org.chervyakovsky.jobsearch.util.mail.MailMessageBuilder;
import org.chervyakovsky.jobsearch.validator.UserInfoValidator;

import java.util.Optional;

import static org.chervyakovsky.jobsearch.controller.AttributeName.INCORRECT_EXIST_LOGIN_OR_EMAIL;
import static org.chervyakovsky.jobsearch.controller.AttributeName.SUCCESSFUL_REGISTRATION;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String COMMAND_ACTIVATE_USER_AND_TOKEN = "command=activate_user&user_token=";
    private static final String COMMAND_RESET_PASSWORD_AND_TOKEN = "command=reset_password&user_token=";

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<UserInfo> authenticate(RequestContent requestContent) throws ServiceException {
        UserInfoValidator validator = UserInfoValidator.getInstance();
        if (validator.isValidLoginUserData(requestContent)) {
            UserDao userDao = UserDaoImpl.getInstance();
            CredentialDao credentialDao = CredentialDaoImpl.getInstance();
            MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
            MapperFromRequestToEntity<Credential> credentialMapper = new CredentialMapperFromRequestToEntity();
            UserInfo userInfo = userInfoMapper.map(requestContent);
            Credential credential = credentialMapper.map(requestContent);
            String login = userInfo.getLogin();
            String password = credential.getPassword();
            try {
                Optional<UserInfo> optionalUserInfo = userDao.findUserByLogin(login);
                Optional<Credential> optionalCredential = credentialDao.findActiveByLogin(login);
                if (optionalCredential.isPresent() && optionalUserInfo.isPresent()) {
                    String passwordDb = optionalCredential.get().getPassword();
                    if (PasswordEncryptor.comparePassword(password, passwordDb)) {
                        return optionalUserInfo;
                    }
                }
            } catch (DaoException exception) {
                LOGGER.log(Level.ERROR, exception); // TODO add comment
                throw new ServiceException(exception); // TODO add comment
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean registrationNewUser(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        UserInfoValidator validator = UserInfoValidator.getInstance();
        if (!validator.isValidRegistrationUserData(requestContent)) {
            return false;
        }
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        MapperFromRequestToEntity<Credential> credentialMapper = new CredentialMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        Credential credential = credentialMapper.map(requestContent);
        String token = RandomStringUtils.randomAlphabetic(20);
        userInfo.setUserToken(token);
        credential.setPassword(PasswordEncryptor.encrypt(credential.getPassword()));
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            if (userDao.existLoginAndEmail(userInfo.getLogin(), userInfo.getEmail())) {
                requestContent.setNewValueInRequestAttributes(INCORRECT_EXIST_LOGIN_OR_EMAIL, true);
            } else if (result = userDao.save(userInfo, credential)) {
                String textMessageMail = MailMessageBuilder.buildMessageContent(COMMAND_ACTIVATE_USER_AND_TOKEN + token);
                Mail.sendMail(userInfo.getEmail(), textMessageMail);
                requestContent.setNewValueInSessionAttribute(SUCCESSFUL_REGISTRATION, true);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return result;
    }

    @Override
    public boolean activateUserAccount(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        String token = userInfo.getUserToken();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<UserInfo> optionalUserInfo = userDao.findUserByToken(token);
            if (optionalUserInfo.isPresent()) {
                userInfo = optionalUserInfo.get();
                userInfo.setUserToken(null);
                userInfo.setUserStatus(true);
                result = userDao.update(userInfo);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return result;
    }

    @Override
    public boolean sendEmailToRecoverPassword(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        String email = userInfo.getEmail();
        String token = RandomStringUtils.randomAlphabetic(20);
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            if (userDao.findUserByEmailAndUpdateUserToken(email, token)) {
                String textMessageMail = MailMessageBuilder.buildMessageContent(COMMAND_RESET_PASSWORD_AND_TOKEN + token);
                Mail.sendMail(userInfo.getEmail(), textMessageMail);
                result = true;
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return result;
    }

    @Override
    public Optional<UserInfo> findUserByToken(RequestContent requestContent) throws ServiceException {
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        String token = userInfo.getUserToken();
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            optionalUserInfo = userDao.findUserByToken(token);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return optionalUserInfo;
    }
    @Override
    public Optional<UserInfo> findUserById(RequestContent requestContent) throws  ServiceException {
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        Long userId = userInfo.getId();
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            optionalUserInfo = userDao.findById(userId);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return optionalUserInfo;
    }

    @Override
    public boolean updateUser(UserInfo userInfo) throws ServiceException {
        boolean result = false;
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            result = userDao.update(userInfo);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return result;
    }

    @Override
    public boolean updateUser(UserInfo userInfo, RequestContent requestContent) throws ServiceException {
        MapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfoFromRequest = userInfoMapper.map(requestContent);
        userInfo.setUserName(userInfoFromRequest.getUserName());
        userInfo.setUserSurName(userInfoFromRequest.getUserSurName());
        userInfo.setEducation(userInfoFromRequest.getEducation());
        userInfo.setProfession(userInfoFromRequest.getProfession());
        userInfo.setWorkingStatus(userInfoFromRequest.getWorkingStatus());
        userInfo.setDescription(userInfoFromRequest.getDescription());
        return updateUser(userInfo);
    }


}
