package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.CredentialDao;
import org.chervyakovsky.jobsearch.model.dao.impl.CredentialDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.CredentialMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.CredentialService;
import org.chervyakovsky.jobsearch.util.PasswordEncryptor;
import org.chervyakovsky.jobsearch.validator.UserInfoValidator;

public class CredentialServiceImpl implements CredentialService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static CredentialServiceImpl instance;

    private CredentialServiceImpl() {
    }

    public static CredentialServiceImpl getInstance() {
        if (instance == null) {
            instance = new CredentialServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean updatePasswordByUserId(RequestContent requestContent, Long userId) throws ServiceException {
        boolean result = false;
        UserInfoValidator validator = UserInfoValidator.getInstance();
        if (validator.isValidatePasswordAndConfirmPassword(requestContent)) {
            MapperFromRequestToEntity<Credential> credentialMapper = new CredentialMapperFromRequestToEntity();
            Credential credential = credentialMapper.map(requestContent);
            credential.setPassword(PasswordEncryptor.encrypt(credential.getPassword()));
            credential.setUserInfoId(userId);
            CredentialDao credentialDao = CredentialDaoImpl.getInstance();
            try {
                result = credentialDao.update(credential);
            } catch (DaoException exception) {
                LOGGER.log(Level.ERROR, exception);
                throw new ServiceException(exception);
            }
        }
        return result;
    }
}
