package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.EducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.WorkingStatus;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserInfoMapperFromRequestToEntity implements CustomMapperFromRequestToEntity<UserInfo> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public UserInfo map(RequestContent requestContent) {
        UserInfo userInfo = new UserInfo();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        String[] loginString = requestParameters.get(ParameterName.USER_LOGIN);
        String[] emailString = requestParameters.get(ParameterName.USER_EMAIL);
        String[] roleString = requestParameters.get(ParameterName.USER_ROLE);
        String[] userStatusString = requestParameters.get(ParameterName.USER_STATUS);
        String[] locationIdString = requestParameters.get(ParameterName.USER_LOCATION);
        String[] userNameString = requestParameters.get(ParameterName.USER_NAME);
        String[] userSurnameString = requestParameters.get(ParameterName.USER_SURNAME);
        String[] workingStatusString = requestParameters.get(ParameterName.USER_WORKING_STATUS);
        String[] educationString = requestParameters.get(ParameterName.USER_EDUCATION_STATUS);
        String[] professionString = requestParameters.get(ParameterName.USER_PROFESSION);
        String[] descriptionString = requestParameters.get(ParameterName.USER_DESCRIPTION);
        String[] userTokenString = requestParameters.get(ParameterName.USER_TOKEN);
        apply(loginString, userInfo::setLogin);
        apply(emailString, userInfo::setEmail);
        apply(roleString, s -> userInfo.setRole(UserRoleStatus.getStatus(s)));
        apply(userStatusString, s -> userInfo.setUserStatus(Boolean.parseBoolean(s)));
        apply(locationIdString, s -> userInfo.setLocationId(Long.parseLong(s)));
        apply(userNameString, userInfo::setUserName);
        apply(userSurnameString, userInfo::setUserSurName);
        apply(workingStatusString, s -> userInfo.setWorkingStatus(WorkingStatus.getStatus(s)));
        apply(educationString, s -> userInfo.setEducation(EducationStatus.getStatus(s)));
        apply(professionString, userInfo::setProfession);
        apply(descriptionString, userInfo::setDescription);
        apply(userTokenString, userInfo::setUserToken);
        return userInfo;
    }


    private void apply(String[] parameterValue, Consumer<String> consumer) {
        if (parameterValue != null && parameterValue.length > 0 && parameterValue[0] != null) {
            consumer.accept(parameterValue[0]);
        }
    }
}
