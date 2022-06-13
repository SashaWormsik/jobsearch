package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.WorkExperienceStatus;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class VacancyMapperFromDbToEntity implements MapperFromDbToEntity<Vacancy> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Vacancy> map(ResultSet resultSet) throws DaoException {
        Vacancy vacancy = new Vacancy();
        Optional<Vacancy> optionalVacancy = Optional.empty();
        try {
            vacancy.setId(resultSet.getLong(ColumnName.VACANCY_ID));
            String dateString = resultSet.getString(ColumnName.VACANCY_CREATE_DATE);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            vacancy.setCreateDate(date);
            vacancy.setJobTitle(resultSet.getString(ColumnName.VACANCY_JOB_TITLE));
            vacancy.setCompanyId(resultSet.getLong(ColumnName.VACANCY_COMPANY_ID));
            vacancy.setLocationId(resultSet.getLong(ColumnName.VACANCY_LOCATION_ID));
            vacancy.setSalary(new BigDecimal(resultSet.getString(ColumnName.VACANCY_SALARY)));
            vacancy.setCurrency(resultSet.getString(ColumnName.VACANCY_CURRENCY));
            vacancy.setWorkExperienceStatus(WorkExperienceStatus.getStatus(resultSet.getString(ColumnName.VACANCY_WORK_EXPERIENCE)));
            vacancy.setResponsibilities(resultSet.getString(ColumnName.VACANCY_RESPONSIBILITIES));
            vacancy.setRequirement(resultSet.getString(ColumnName.VACANCY_REQUIREMENT));
            vacancy.setWorkingConditions(resultSet.getString(ColumnName.VACANCY_WORKING_CONDITION));
            vacancy.setVacancyStatus(resultSet.getBoolean(ColumnName.VACANCY_STATUS));
            optionalVacancy = Optional.of(vacancy);
        } catch (SQLException | ParseException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new DaoException(exception); // TODO add comment
        }
        return optionalVacancy;
    }
}
