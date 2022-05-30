package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.EducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.WorkingStatus;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromDbToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserInfoMapperFromDbToEntity implements CustomMapperFromDbToEntity<UserInfo> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<UserInfo> map(ResultSet resultSet) throws DaoException {
        UserInfo userInfo = new UserInfo();
        Optional<UserInfo> optionalUserInfo;
        try {
            userInfo.setId(resultSet.getLong(ColumnName.USER_ID));
            userInfo.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            userInfo.setEmail(resultSet.getString(ColumnName.USER_MAIL));
            userInfo.setUserStatus(resultSet.getBoolean(ColumnName.USER_STATUS));
            userInfo.setLocationId(NumberUtils.createLong(resultSet.getString(ColumnName.USER_LOCATION)));
            userInfo.setUserName(resultSet.getString(ColumnName.USER_NAME));
            userInfo.setUserSurName(resultSet.getString(ColumnName.USER_SURNAME));
            userInfo.setProfession(resultSet.getString(ColumnName.USER_PROFESSION));
            userInfo.setDescription(resultSet.getString(ColumnName.USER_DESCRIPTION));
            userInfo.setUserToken(resultSet.getString(ColumnName.USER_TOKEN));
            userInfo.setRole(UserRoleStatus.getStatus(resultSet.getString(ColumnName.USER_ROLE)));
            userInfo.setWorkingStatus(WorkingStatus.getStatus(resultSet.getString(ColumnName.USER_WORKING_STATUS)));
            userInfo.setEducation(EducationStatus.getStatus(resultSet.getString(ColumnName.USER_EDUCATION)));
            optionalUserInfo = Optional.of(userInfo);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new DaoException(exception); // TODO add comment
        }
        return optionalUserInfo;
    }
}
