package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    boolean createNewInterview(RequestContent requestContent) throws ServiceException;

    boolean updateInterview(RequestContent requestContent) throws ServiceException;

    boolean changeInterviewStatus(RequestContent requestContent) throws ServiceException;

    Optional<Interview> findInterviewById(RequestContent requestContent) throws ServiceException;

    List<Interview> findAllUserInterview(RequestContent requestContent) throws ServiceException;
}
