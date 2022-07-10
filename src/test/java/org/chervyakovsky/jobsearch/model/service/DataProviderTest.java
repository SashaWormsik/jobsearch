package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.status.EducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.WorkingStatus;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class DataProviderTest {

    @DataProvider(name = "data-provider")
    public Object[][] createData() {
        RequestContent requestContent = new RequestContent();
        Map<String, String[]> parameters = requestContent.getRequestParameters();
        Map<String, Object> sessionAttribute = requestContent.getSessionAttribute();
        parameters.put(ParameterName.USER_ID, new String[]{"1"});
        parameters.put(ParameterName.USER_LOGIN, new String[]{"@SASHA"});
        parameters.put(ParameterName.USER_EMAIL, new String[]{"sasha@mail.ru"});
        parameters.put(ParameterName.USER_ROLE, new String[]{UserRoleStatus.WORKER.name()});
        parameters.put(ParameterName.USER_STATUS, new String[]{"true"});
        parameters.put(ParameterName.USER_LOCATION, new String[]{"2"});
        parameters.put(ParameterName.USER_NAME, new String[]{"Sasha"});
        parameters.put(ParameterName.USER_SURNAME, new String[]{"Cherviak"});
        parameters.put(ParameterName.USER_WORKING_STATUS, new String[]{WorkingStatus.WORK.name()});
        parameters.put(ParameterName.USER_EDUCATION_STATUS, new String[]{EducationStatus.HIGHER.name()});
        parameters.put(ParameterName.USER_PROFESSION, new String[]{"Driver"});
        parameters.put(ParameterName.USER_DESCRIPTION, new String[]{"Description"});
        parameters.put(ParameterName.USER_TOKEN, new String[]{"ASD123"});
        parameters.put(ParameterName.LOCATION_ID, new String[]{"2"});
        parameters.put(ParameterName.LOCATION_CITY, new String[]{"Brest"});
        parameters.put(ParameterName.LOCATION_COUNTRY, new String[]{"Belarus"});


        Object[][] data = new Object[1][1];
        return data;
    }
}
