package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CredentialMapperFromDbToEntity implements MapperFromDbToEntity<Credential> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Credential> map(ResultSet resultSet) throws DaoException {
        Credential credential = new Credential();
        Optional<Credential> optionalCredential;
        try {
            credential.setId(resultSet.getLong(ColumnName.CREDENTIAL_ID));
            credential.setPassword(resultSet.getString(ColumnName.CREDENTIAL_PASSWORD));
            credential.setActive(resultSet.getBoolean(ColumnName.CREDENTIAL_ACTIVE));
            credential.setCreateDate(resultSet.getDate(ColumnName.CREDENTIAL_CREATE_DATE));
            credential.setUserInfoId(resultSet.getLong(ColumnName.CREDENTIAL_USER_INFO_ID));
            optionalCredential = Optional.of(credential);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalCredential;
    }
}
