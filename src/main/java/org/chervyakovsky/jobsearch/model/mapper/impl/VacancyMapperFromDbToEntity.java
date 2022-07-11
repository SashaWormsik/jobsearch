package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.WorkExperienceStatus;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The type VacancyMapperFromDbToEntity class. Maps result set to the Vacancy class object.
 */
public class VacancyMapperFromDbToEntity implements MapperFromDbToEntity<Vacancy> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Vacancy> map(ResultSet resultSet) throws DaoException {
        Vacancy vacancy = new Vacancy();
        Optional<Vacancy> optionalVacancy = Optional.empty();
        try {
            vacancy.setId(resultSet.getLong(ColumnName.VACANCY_ID));
            vacancy.setCreateDate(resultSet.getDate(ColumnName.VACANCY_CREATE_DATE));
            vacancy.setJobTitle(resultSet.getString(ColumnName.VACANCY_JOB_TITLE));
            vacancy.setCompanyId(resultSet.getLong(ColumnName.VACANCY_COMPANY_ID));
            vacancy.setLocationId(resultSet.getLong(ColumnName.VACANCY_LOCATION_ID));
            vacancy.setSalary(resultSet.getBigDecimal(ColumnName.VACANCY_SALARY));
            vacancy.setCurrency(resultSet.getString(ColumnName.VACANCY_CURRENCY));
            vacancy.setWorkExperienceStatus(WorkExperienceStatus.getStatus(resultSet.getString(ColumnName.VACANCY_WORK_EXPERIENCE)));
            vacancy.setResponsibilities(resultSet.getString(ColumnName.VACANCY_RESPONSIBILITIES));
            vacancy.setRequirement(resultSet.getString(ColumnName.VACANCY_REQUIREMENT));
            vacancy.setWorkingConditions(resultSet.getString(ColumnName.VACANCY_WORKING_CONDITION));
            vacancy.setVacancyStatus(resultSet.getBoolean(ColumnName.VACANCY_STATUS));
            optionalVacancy = Optional.of(vacancy);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalVacancy;
    }
}
