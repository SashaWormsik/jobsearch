package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.EnumEducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.EnumUserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.EnumWorkingStatus;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserInfoMapper implements CustomMapper<UserInfo> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<UserInfo> map(ResultSet resultSet) throws DaoException {
        UserInfo userInfo = new UserInfo();
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        try {
            userInfo.setId(Long.parseLong(resultSet.getString(ColumnName.USER_ID)));
            userInfo.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            userInfo.setMail(resultSet.getString(ColumnName.USER_MAIL));
            EnumUserRoleStatus roleStatus = EnumUserRoleStatus.valueOf(resultSet.getString(ColumnName.USER_ROLE));
            userInfo.setRole(roleStatus);
            userInfo.setUserStatus(resultSet.getBoolean(ColumnName.USER_STATUS));
            userInfo.setLocationId(resultSet.getLong(ColumnName.USER_LOCATION));
            userInfo.setUserName(resultSet.getString(ColumnName.USER_NAME));
            userInfo.setUserSurName(resultSet.getString(ColumnName.USER_SURNAME));
            EnumWorkingStatus workingStatus = EnumWorkingStatus.valueOf(resultSet.getString(ColumnName.USER_WORKING_STATUS));
            userInfo.setWorkingStatus(workingStatus);
            EnumEducationStatus educationStatus = EnumEducationStatus.valueOf(resultSet.getString(ColumnName.USER_EDUCATION));
            userInfo.setEducation(educationStatus);
            userInfo.setProfession(resultSet.getString(ColumnName.USER_PROFESSION));
            userInfo.setDescription(resultSet.getString(ColumnName.USER_DESCRIPTION));
            userInfo.setUserToken(resultSet.getString(ColumnName.USER_TOKEN));
            optionalUserInfo = Optional.of(userInfo);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new DaoException(exception); // TODO add comment
        }
        return optionalUserInfo;
    }
}
