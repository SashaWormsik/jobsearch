package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InterviewMapperFromDbToEntity implements MapperFromDbToEntity<Interview> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Interview> map(ResultSet resultSet) throws DaoException {
        Interview interview = new Interview();
        Optional<Interview> optionalInterview = Optional.empty();
        try {
            interview.setId(resultSet.getLong(ColumnName.INTERVIEW_ID));
            interview.setCommunicationMethod(resultSet.getString(ColumnName.INTERVIEW_COMMUNICATION_METHOD));
            interview.setUserInfoId(resultSet.getLong(ColumnName.INTERVIEW_WORKER_ID));
            interview.setVacancyId(resultSet.getLong(ColumnName.INTERVIEW_VACANCY_ID));
            interview.setInterviewStatus(InterviewStatus.getStatus(resultSet.getString(ColumnName.INTERVIEW_STATUS)));
            interview.setAppointedDateTime(resultSet.getTimestamp(ColumnName.INTERVIEW_APPOINTED_DATE));
            optionalInterview = Optional.of(interview);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalInterview;
    }
}
