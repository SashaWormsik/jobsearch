package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Interview;

import java.util.List;

/**
 * The interface InterviewDao.
 */
public interface InterviewDao extends BaseDao<Interview> {
    /**
     * Checks if there are interviews with current vacancyId and workerId.
     *
     * @param vacancyId vacancy id
     * @param workerId  user id
     * @return true if there is such an interview, otherwise false
     * @throws DaoException if the was any SQLException during database operations.
     */
    boolean isPresent(long vacancyId, long workerId) throws DaoException;

    /**
     * Changes the status of interviews that have expired to IS_REJECTED
     *
     * @throws DaoException if the was any SQLException during database operations.
     */
    void editOverdueInterviews() throws DaoException;

    /**
     * Returns a list of interviews for this worker.
     *
     * @param id user id (worker)
     * @return a list of interviews for this worker.
     * @throws DaoException if the was any SQLException during database operations.
     */
    List<Interview> findInterviewByWorkerId(long id) throws DaoException;

    /**
     * Returns a list of interviews for this company.
     *
     * @param id user id (company)
     * @return a list of interviews for this company.
     * @throws DaoException
     */
    List<Interview> findInterviewByCompanyId(long id) throws DaoException;

}
