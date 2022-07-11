package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.WorkExperienceStatus;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * The type VacancyMapperFromRequestToEntity class.
 * Maps a set of parameters from the RequestContent object to the Vacancy class object.
 */
public class VacancyMapperFromRequestToEntity implements MapperFromRequestToEntity<Vacancy> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Vacancy map(RequestContent requestContent) {
        Vacancy vacancy = new Vacancy();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        String[] id = requestParameters.get(ParameterName.VACANCY_ID);
        String[] createDate = requestParameters.get(ParameterName.VACANCY_CREATE_DATE);
        String[] jobTitle = requestParameters.get(ParameterName.VACANCY_JOB_TITLE);
        String[] companyId = requestParameters.get(ParameterName.VACANCY_COMPANY_ID);
        String[] locationId = requestParameters.get(ParameterName.VACANCY_LOCATION_ID);
        String[] salary = requestParameters.get(ParameterName.VACANCY_SALARY);
        String[] currency = requestParameters.get(ParameterName.VACANCY_CURRENCY);
        String[] workExperience = requestParameters.get(ParameterName.VACANCY_WORK_EXPERIENCE);
        String[] responsibilities = requestParameters.get(ParameterName.VACANCY_RESPONSIBILITIES);
        String[] requirement = requestParameters.get(ParameterName.VACANCY_REQUIREMENTS);
        String[] workingConditions = requestParameters.get(ParameterName.VACANCY_WORKING_CONDITIONS);
        String[] status = requestParameters.get(ParameterName.VACANCY_STATUS);
        apply(id, s -> vacancy.setId(Long.parseLong(s)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        apply(createDate, s -> {
            try {
                vacancy.setCreateDate(simpleDateFormat.parse(s));
            } catch (ParseException exception) {
                LOGGER.log(Level.ERROR, exception);
            }
        });
        apply(jobTitle, vacancy::setJobTitle);
        apply(companyId, s -> vacancy.setCompanyId(Long.parseLong(s)));
        apply(locationId, s -> vacancy.setLocationId(Long.parseLong(s)));
        apply(salary, s -> vacancy.setSalary(new BigDecimal(s)));
        apply(currency, vacancy::setCurrency);
        apply(workExperience, s -> vacancy.setWorkExperienceStatus(WorkExperienceStatus.getStatus(s)));
        apply(responsibilities, vacancy::setResponsibilities);
        apply(requirement, vacancy::setRequirement);
        apply(workingConditions, vacancy::setWorkingConditions);
        apply(status, s -> vacancy.setVacancyStatus(Boolean.parseBoolean(s)));
        return vacancy;
    }

    private void apply(String[] parameterValue, Consumer<String> consumer) {
        if (parameterValue != null && parameterValue.length > 0 && parameterValue[0] != null) {
            consumer.accept(parameterValue[0]);
        }
    }

}
