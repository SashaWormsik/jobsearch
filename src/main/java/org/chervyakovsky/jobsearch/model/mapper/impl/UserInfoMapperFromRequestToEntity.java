package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.EnumEducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.EnumUserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.EnumWorkingStatus;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.Optional;

public class UserInfoMapperFromRequestToEntity implements CustomMapperFromRequestToEntity<UserInfo> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public UserInfo map(RequestContent requestContent) {
        UserInfo userInfo = new UserInfo();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        userInfo.setLogin(requestParameters.get(ParameterName.USER_LOGIN)[0]);
        userInfo.setEmail(requestParameters.get(ParameterName.USER_EMAIL)[0]);
        EnumUserRoleStatus role = EnumUserRoleStatus.valueOf(requestParameters.get(ParameterName.USER_ROLE)[0]);
        userInfo.setRole(role);
        boolean userStatus = Boolean.parseBoolean(requestParameters.get(ParameterName.USER_STATUS)[0]);
        userInfo.setUserStatus(userStatus);
        long locationId = Long.parseLong(requestParameters.get(ParameterName.USER_LOCATION)[0]);
        userInfo.setLocationId(locationId);
        userInfo.setUserName(requestParameters.get(ParameterName.USER_NAME)[0]);
        userInfo.setUserSurName(requestParameters.get(ParameterName.USER_SURNAME)[0]);
        EnumWorkingStatus workingStatus = EnumWorkingStatus.valueOf(requestParameters.get(ParameterName.USER_WORKING_STATUS)[0]);
        userInfo.setWorkingStatus(workingStatus);
        EnumEducationStatus educationStatus = EnumEducationStatus.valueOf(requestParameters.get(ParameterName.USER_EDUCATION_STATUS)[0]);
        userInfo.setEducation(educationStatus);
        userInfo.setProfession(requestParameters.get(ParameterName.USER_PROFESSION)[0]);
        userInfo.setDescription(requestParameters.get(ParameterName.USER_DESCRIPTION)[0]);
        userInfo.setUserToken(requestParameters.get(ParameterName.USER_TOKEN)[0]);
        return userInfo;
    }
}
