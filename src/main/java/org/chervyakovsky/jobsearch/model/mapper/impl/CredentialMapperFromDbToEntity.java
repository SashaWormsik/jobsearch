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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            String dateString = resultSet.getString(ColumnName.CREDENTIAL_CREATE_DATE);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            credential.setCreateDate(date);
            credential.setUserInfoId(resultSet.getLong(ColumnName.CREDENTIAL_USER_INFO_ID));
            optionalCredential = Optional.of(credential);
        } catch (SQLException | ParseException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new DaoException(exception); // TODO add comment
        }
        return optionalCredential;
    }
}
