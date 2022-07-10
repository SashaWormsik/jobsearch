package org.chervyakovsky.jobsearch;

import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.*;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class DataProviderTest {

    @DataProvider(name = "data-provider")
    public Object[][] createData() {
        RequestContent requestContent = new RequestContent();
        Map<String, String[]> parameters = requestContent.getRequestParameters();
        Map<String, Object> sessionAttribute = requestContent.getSessionAttribute();

        UserInfo user = new UserInfo();
        user.setUserStatus(true);
        user.setDescription("cool");
        user.setId(1L);
        user.setUserName("Sasha");
        user.setProfession("Driver");
        user.setEducation(EducationStatus.HIGHER);
        user.setUserSurName("Worms");
        user.setWorkingStatus(WorkingStatus.IN_SEARCH);
        user.setLocationId(2L);
        user.setEmail("sasha@mail.com");
        user.setRole(UserRoleStatus.WORKER);

        Location location = new Location();
        location.setId(2L);
        location.setCity("Brest");
        location.setCountry("Belarus");
        sessionAttribute.put(AttributeName.USER, user);
        sessionAttribute.put(AttributeName.USER_LOCATION, location);

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
        parameters.put(ParameterName.USER_SEARCH_QUERY, new String[]{"sasha"});

        parameters.put(ParameterName.CREDENTIAL_USER_ID, new String[]{"1"});
        parameters.put(ParameterName.CREDENTIAL_ACTIVE, new String[]{"true"});
        parameters.put(ParameterName.CREDENTIAL_PASSWORD, new String[]{"24081989alex"});
        parameters.put(ParameterName.CREDENTIAL_CONFIRM_PASSWORD, new String[]{"24081989alex"});
        parameters.put(ParameterName.CREDENTIAL_CREATE_DATA, new String[]{"2022-07-22"});

        parameters.put(ParameterName.VACANCY_ID, new String[]{"3"});
        parameters.put(ParameterName.VACANCY_CREATE_DATE, new String[]{"2022-07-22"});
        parameters.put(ParameterName.VACANCY_JOB_TITLE, new String[]{"Driver"});
        parameters.put(ParameterName.VACANCY_COMPANY_ID, new String[]{"5"});
        parameters.put(ParameterName.VACANCY_LOCATION_ID, new String[]{"2"});
        parameters.put(ParameterName.VACANCY_SALARY, new String[]{"2000.00"});
        parameters.put(ParameterName.VACANCY_CURRENCY, new String[]{"BYN"});
        parameters.put(ParameterName.VACANCY_WORK_EXPERIENCE, new String[]{WorkExperienceStatus.FROM_1_TO_3_YEARS.name()});
        parameters.put(ParameterName.VACANCY_RESPONSIBILITIES, new String[]{"ездить"});
        parameters.put(ParameterName.VACANCY_REQUIREMENTS, new String[]{"права"});
        parameters.put(ParameterName.VACANCY_WORKING_CONDITIONS, new String[]{"отпуск"});
        parameters.put(ParameterName.VACANCY_STATUS, new String[]{"true"});

        parameters.put(ParameterName.INTERVIEW_ID, new String[]{"6"});
        parameters.put(ParameterName.INTERVIEW_APPOINTED_DATE, new String[]{"2025-08-02T12:15"});
        parameters.put(ParameterName.INTERVIEW_STATUS, new String[]{InterviewStatus.IN_WAITING.name()});
        parameters.put(ParameterName.INTERVIEW_VACANCY_ID, new String[]{"3"});
        parameters.put(ParameterName.INTERVIEW_WORKER_ID, new String[]{"1"});
        parameters.put(ParameterName.INTERVIEW_COMMUNICATION_METHOD, new String[]{"gmail.com"});

        parameters.put(ParameterName.LOCATION_ID, new String[]{"2"});
        parameters.put(ParameterName.LOCATION_CITY, new String[]{"Brest"});
        parameters.put(ParameterName.LOCATION_COUNTRY, new String[]{"Belarus"});

        Object[][] data = new Object[1][1];
        data[0][0] = requestContent;
        return data;
    }
}
