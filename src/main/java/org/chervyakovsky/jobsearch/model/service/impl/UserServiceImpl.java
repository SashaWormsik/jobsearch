package org.chervyakovsky.jobsearch.model.service.impl;

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
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.util.PasswordEncryptor;
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
    public Optional<UserInfo> authenticate(String login, String password) throws ServiceException { // fixme
        UserInfoValidator validator = UserInfoValidator.getInstance();
        Optional<UserInfo> optionalUserInfo;
        Optional<Credential> optionalCredential;
        if (!validator.validateLogin(login) || !validator.validatePassword(password)) {
            return Optional.empty();
        }
        UserDao userDao = UserDaoImpl.getInstance();
        CredentialDao credentialDao = CredentialDaoImpl.getInstance();
        try {
            optionalUserInfo = userDao.findUserByLogin(login);
            optionalCredential = credentialDao.findActiveByLogin(login);
            if(optionalCredential.isPresent() && optionalUserInfo.isPresent()){
                String passwordDb = optionalCredential.get().getPassword();
                if (PasswordEncryptor.comparePassword(password, passwordDb)){
                    return optionalUserInfo;
                }
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception); // TODO add comment
        }
        return Optional.empty();
    }

    @Override
    public boolean register() {
        return false;
    }
}
