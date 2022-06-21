package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.InterviewDao;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InterviewDaoImpl implements InterviewDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String INSERT_NEW_INTERVIEW =
            "INSERT INTO interview (i_appointed_date, i_interview_status, i_vacancy_id, i_worker_id, i_communication_method) " +
                    "VALUES (?, ?::interview_status_enum, ?, ?, ?)";

    private static final String UPDATE_INTERVIEW =
            "SET i_appointed_date = ?, i_interview_status = ?::interview_status_enum, i_communication_method = ? " +
                    "WHERE i_interview_id = ?";

    private static final String SET_NEW_INTERVIEW_STATUS =
            "UPDATE interview " +
                    "SET i_interview_status = ?::interview_status_enum" +
                    "WHERE i_interview_id = ?";

    private static final String SELECT_ALL_WORKER_INTERVIEW =
            "SELECT * FROM interview " +
                    "JOIN vacancy ON vacancy.v_vacancy_id = interview.i_vacancy_id " +
                    "JOIN user_info ON user_info.u_user_info_id = vacancy.v_company_id " +
                    "WHERE i_worker_id = ? " +
                    "ORDER BY i_appointed_date";

    private static final String SELECT_ALL_COMPANY_INTERVIEW =
            "SELECT * FROM interview " +
                    "JOIN vacancy ON vacancy.v_vacancy_id = interview.i_vacancy_id " +
                    "JOIN user_info ON user_info.u_user_info_id = vacancy.v_company_id " +
                    "WHERE u_user_info_id = ? " +
                    "ORDER BY i_appointed_date";

    private static final String SELECT_ALL_VACANCIES_INTERVIEW_IN_WAITING_OR_IS_REJECTED =
            "SELECT * FROM interview " +
                    "JOIN user_info ON user_info.u_user_info_id = interview.i_worker_id " +
                    "WHERE i_vacancy_id = ? AND i_interview_status IN ('IN_WAITING', 'IS_REJECTED') " +
                    "ORDER BY i_appointed_date";

    private static final String SELECT_ALL_VACANCIES_INTERVIEW_IS_SCHEDULED_OR_IS_COMPLETED =
            "SELECT * FROM interview " +
                    "JOIN user_info ON user_info.u_user_info_id = interview.i_worker_id " +
                    "WHERE i_vacancy_id = ? AND i_interview_status IN ('IS_SCHEDULED', 'IS_COMPLETED') " +
                    "ORDER BY i_appointed_date";

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
    public Optional<Interview> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean insert(Interview interview) throws DaoException {
        return false;
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
    public boolean update(Interview interview) throws DaoException {
        return false;
    }

    @Override
    public HashMap<Interview, Map.Entry<Vacancy, Location>> findInterviewByWorkerId(long id) throws DaoException {
        return null;
    }

    @Override
    public HashMap<Interview, Map.Entry<Vacancy, Location>> findInterviewByCompanyId(long id) throws DaoException {
        return null;
    }

    @Override
    public boolean setNewInterviewStatusById(long id, InterviewStatus status) throws DaoException {
        return false;
    }
}
