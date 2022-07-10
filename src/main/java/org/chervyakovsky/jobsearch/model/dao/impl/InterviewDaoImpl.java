package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.InterviewDao;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.InterviewMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InterviewDaoImpl implements InterviewDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_EXISTS_INTERVIEW =
            "SELECT EXISTS (SELECT i_interview_id FROM interview WHERE i_vacancy_id = ? AND i_worker_id = ?)";

    private static final String INSERT_NEW_INTERVIEW =
            "INSERT INTO interview (i_appointed_date, i_interview_status, i_vacancy_id, i_worker_id, i_communication_method) " +
                    "VALUES (?, ?::interview_status_enum, ?, ?, ?)";

    private static final String SELECT_INTERVIEW_BY_ID =
            "SELECT * FROM interview WHERE i_interview_id = ?";

    private static final String UPDATE_INTERVIEW =
            "UPDATE interview " +
                    "SET i_appointed_date = ?, i_interview_status = ?::interview_status_enum, i_communication_method = ? " +
                    "WHERE i_interview_id = ?";

    private static final String SELECT_ALL_WORKER_INTERVIEW =
            "SELECT * FROM interview " +
                    "WHERE i_worker_id = ? " +
                    "ORDER BY i_appointed_date DESC";

    private static final String SELECT_ALL_COMPANY_INTERVIEW =
            "SELECT * FROM interview " +
                    "WHERE i_vacancy_id IN " +
                    "(SELECT v_vacancy_id FROM vacancy " +
                    "WHERE v_company_id = ?) " +
                    "ORDER BY i_appointed_date DESC";

    private static final String UPDATE_ALL_WHERE_APPOINTED_TIME_OUT =
            "UPDATE interview " +
                    "SET i_interview_status = 'IS_REJECTED'::interview_status_enum " +
                    "WHERE i_interview_status LIKE 'IS_SCHEDULED'::interview_status_enum " +
                    "AND i_appointed_date < ?";

    private static InterviewDaoImpl instance;

    private InterviewDaoImpl() {
    }

    public static InterviewDaoImpl getInstance() {
        if (instance == null) {
            instance = new InterviewDaoImpl();
        }
        return instance;
    }

    @Override
    public void editOverdueInterviews() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ALL_WHERE_APPOINTED_TIME_OUT)) {
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean update(Interview interview) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_INTERVIEW)) {
            statement.setTimestamp(1, new Timestamp(interview.getAppointedDateTime().getTime()));
            statement.setObject(2, interview.getInterviewStatus(), Types.OTHER);
            statement.setString(3, interview.getCommunicationMethod());
            statement.setLong(4, interview.getId());
            int row = statement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    @Override
    public List<Interview> findInterviewByWorkerId(long id) throws DaoException {
        return findInterviewsByUserId(id, SELECT_ALL_WORKER_INTERVIEW);
    }

    @Override
    public List<Interview> findInterviewByCompanyId(long id) throws DaoException {
        return findInterviewsByUserId(id, SELECT_ALL_COMPANY_INTERVIEW);
    }

    @Override
    public boolean insert(Interview interview) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_INTERVIEW)) {
            statement.setTimestamp(1, new Timestamp(interview.getAppointedDateTime().getTime()));
            statement.setObject(2, interview.getInterviewStatus(), Types.OTHER);
            statement.setLong(3, interview.getVacancyId());
            statement.setLong(4, interview.getUserInfoId());
            statement.setString(5, interview.getCommunicationMethod());
            int row = statement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    @Override
    public boolean isPresent(long vacancyId, long workerId) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXISTS_INTERVIEW)) {
            statement.setLong(1, vacancyId);
            statement.setLong(2, workerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getBoolean(1);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return result;
    }

    @Override
    public Optional<Interview> findById(long id) throws DaoException {
        Optional<Interview> optionalInterview = Optional.empty();
        MapperFromDbToEntity<Interview> mapper = new InterviewMapperFromDbToEntity();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_INTERVIEW_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalInterview = mapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }

        return optionalInterview;
    }

    @Override
    public boolean delete(Interview interview) throws DaoException {
        return false;
    }

    @Override
    public List<Interview> findAll() throws DaoException {

        return null;
    }

    @Override
    public boolean setNewInterviewStatusById(long id, InterviewStatus status) throws DaoException {
        return false;
    }

    private List<Interview> findInterviewsByUserId(long id, String selectAllWorkerInterview) throws DaoException {
        MapperFromDbToEntity<Interview> mapper = new InterviewMapperFromDbToEntity();
        List<Interview> interviewList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllWorkerInterview)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Interview> interview = mapper.map(resultSet);
                    interview.ifPresent(interviewList::add);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return interviewList;
    }
}
