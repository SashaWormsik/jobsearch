package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.List;
import java.util.Optional;

/**
 * The interface InterviewService.
 */
public interface InterviewService {

    /**
     * Saves a new interview.
     *
     * @param requestContent must contain values for interview
     * @return the boolean {@code true} if created
     * @throws ServiceException the service exception.
     */
    boolean createNewInterview(RequestContent requestContent) throws ServiceException;

    /**
     * Updates the interview.
     *
     * @param requestContent must contain values for interview
     * @return the boolean {@code true} if updated
     * @throws ServiceException the service exception.
     */
    boolean updateInterview(RequestContent requestContent) throws ServiceException;

    /**
     * @param requestContent must contain values for interview
     * @return the boolean {@code true}  if the status has been changed.
     * @throws ServiceException the service exception.
     */
    boolean changeInterviewStatus(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for interviews by id.
     *
     * @param requestContent must contain values for interview
     * @return interview optional
     * @throws ServiceException the service exception.
     */
    Optional<Interview> findInterviewById(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for interviews for this user.
     *
     * @param requestContent must contain values for interview and user
     * @return a list of interviews
     * @throws ServiceException the service exception.
     */
    List<Interview> findAllUserInterview(RequestContent requestContent) throws ServiceException;
}
