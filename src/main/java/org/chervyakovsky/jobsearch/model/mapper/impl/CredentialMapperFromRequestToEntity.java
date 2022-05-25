package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.function.Consumer;

public class CredentialMapperFromRequestToEntity implements CustomMapperFromRequestToEntity<Credential> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Credential map(RequestContent requestContent) {
        Credential credential = new Credential();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        String[] password = requestParameters.get(ParameterName.CREDENTIAL_PASSWORD);
        String[] active = requestParameters.get(ParameterName.CREDENTIAL_ACTIVE);
        String[] createData = requestParameters.get(ParameterName.CREDENTIAL_CREATE_DATA);
        String[] userInfoId = requestParameters.get(ParameterName.CREDENTIAL_USER_ID);
        apply(password, credential::setPassword);
        apply(active, s -> credential.setActive(Boolean.parseBoolean(s)));
        apply(userInfoId, s -> credential.setUserInfoId(Long.parseLong(s)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        apply(createData, s -> {
            try {
                credential.setCreateDate(simpleDateFormat.parse(s)); // fixme
            } catch (ParseException exception) {
                LOGGER.log(Level.ERROR, exception); // TODO add comment
            }
        });
        return credential;
    }

    private void apply(String[] parameterValue, Consumer<String> consumer) {
        if (parameterValue != null && parameterValue.length > 0 && parameterValue[0] != null) {
            consumer.accept(parameterValue[0]);
        }
    }
}