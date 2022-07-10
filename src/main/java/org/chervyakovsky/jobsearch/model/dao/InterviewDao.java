package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InterviewDao extends BaseDao<Interview> {

    boolean isPresent(long vacancyId, long workerId) throws DaoException;

    void editOverdueInterviews() throws DaoException;

    List<Interview> findInterviewByWorkerId(long id) throws DaoException;

    List<Interview> findInterviewByCompanyId(long id) throws DaoException;

    boolean setNewInterviewStatusById(long id, InterviewStatus status) throws DaoException;
}
