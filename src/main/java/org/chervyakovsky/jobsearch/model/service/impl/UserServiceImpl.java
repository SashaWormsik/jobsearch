package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.CredentialDao;
import org.chervyakovsky.jobsearch.model.dao.UserDao;
import org.chervyakovsky.jobsearch.model.dao.impl.CredentialDaoImpl;
import org.chervyakovsky.jobsearch.model.dao.impl.UserDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.CredentialMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.util.PasswordEncryptor;
import org.chervyakovsky.jobsearch.util.mail.Mail;
import org.chervyakovsky.jobsearch.util.mail.MailMessageBuilder;
import org.chervyakovsky.jobsearch.validator.UserInfoValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

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
            CustomMapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
            CustomMapperFromRequestToEntity<Credential> credentialMapper = new CredentialMapperFromRequestToEntity();
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
        UserDao userDao = UserDaoImpl.getInstance();
        if (!validator.isValidRegistrationUserData(requestContent)) {
            return false;
        }
        CustomMapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        CustomMapperFromRequestToEntity<Credential> credentialMapper = new CredentialMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        Credential credential = credentialMapper.map(requestContent);
        String token = RandomStringUtils.randomAlphabetic(20); // FIXME
        userInfo.setUserToken(token);
        credential.setPassword(PasswordEncryptor.encrypt(credential.getPassword()));
        try {
            if (userDao.existLoginAndEmail(userInfo.getLogin(), userInfo.getEmail())) {
                requestContent.setNewValueInRequestAttributes(AttributeName.EXIST_LOGIN_OR_EMAIL, true);
            } else if (result = userDao.save(userInfo, credential)) { // FIXME
                String textMessageMail = MailMessageBuilder.buildMessageContent(token);
                Mail.sendMail(userInfo.getEmail(), textMessageMail);
                requestContent.setNewValueInSessionAttribute(AttributeName.SUCCESSFUL_REGISTRATION, true);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception); // TODO add comment
        }
        return result;
    }

    @Override
    public boolean existLoginAndEmail(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        CustomMapperFromRequestToEntity<UserInfo> userInfoMapper = new UserInfoMapperFromRequestToEntity();
        UserInfo userInfo = userInfoMapper.map(requestContent);
        UserDao userDao = UserDaoImpl.getInstance();
        String login = userInfo.getLogin();
        String email = userInfo.getEmail();
        try {
            result = userDao.existLoginAndEmail(login, email);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception); // TODO add comment
        }
        return result;
    }
}
