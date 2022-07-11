package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.InterviewDao;
import org.chervyakovsky.jobsearch.model.dao.impl.InterviewDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.InterviewMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.VacancyMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.InterviewService;
import org.chervyakovsky.jobsearch.validator.InterviewValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterviewServiceImpl implements InterviewService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static InterviewServiceImpl instance;

    private InterviewServiceImpl() {
    }

    public static InterviewServiceImpl getInstance() {
        if (instance == null) {
            instance = new InterviewServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createNewInterview(RequestContent requestContent) throws ServiceException {
        boolean result;
        UserInfo userInfo = (UserInfo) requestContent.getSessionAttribute().get(AttributeName.USER);
        MapperFromRequestToEntity<Vacancy> mapper = new VacancyMapperFromRequestToEntity();
        Vacancy vacancy = mapper.map(requestContent);
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        try {
            if (interviewDao.isPresent(vacancy.getId(), userInfo.getId())) {
                return false;
            } else {
                Interview interview = new Interview();
                interview.setVacancyId(vacancy.getId());
                interview.setUserInfoId(userInfo.getId());
                interview.setCommunicationMethod(userInfo.getEmail());
                result = interviewDao.insert(interview);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public boolean updateInterview(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        InterviewValidator validator = InterviewValidator.getInstance();
        if(!validator.isValidInterviewData(requestContent)){
            return false;
        }
        Optional<Interview> optionalInterview;
        MapperFromRequestToEntity<Interview> mapper = new InterviewMapperFromRequestToEntity();
        Interview interview = mapper.map(requestContent);
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        try {
            optionalInterview = interviewDao.findById(interview.getId());
            if (optionalInterview.isPresent()) {
                Interview interviewFromDb = optionalInterview.get();
                interviewFromDb.setInterviewStatus(interview.getInterviewStatus());
                interviewFromDb.setAppointedDateTime(interview.getAppointedDateTime());
                interviewFromDb.setCommunicationMethod(interview.getCommunicationMethod());
                if (interviewDao.update(interviewFromDb)) {
                    result = true;
                }
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public boolean changeInterviewStatus(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        Optional<Interview> optionalInterview;
        MapperFromRequestToEntity<Interview> mapper = new InterviewMapperFromRequestToEntity();
        Interview interview = mapper.map(requestContent);
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        try {
            optionalInterview = interviewDao.findById(interview.getId());
            if (optionalInterview.isPresent()) {
                Interview interviewFromDb = optionalInterview.get();
                if (interviewFromDb.getInterviewStatus().equals(InterviewStatus.IN_WAITING) ||
                        interviewFromDb.getInterviewStatus().equals(InterviewStatus.IS_SCHEDULED)) {
                    interviewFromDb.setInterviewStatus(interview.getInterviewStatus());
                    if (interviewDao.update(interviewFromDb)) {
                        result = true;
                    }
                }
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public Optional<Interview> findInterviewById(RequestContent requestContent) throws ServiceException {
        Optional<Interview> optionalInterview = Optional.empty();
        MapperFromRequestToEntity<Interview> mapper = new InterviewMapperFromRequestToEntity();
        Interview interview = mapper.map(requestContent);
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        try {
            optionalInterview = interviewDao.findById(interview.getId());
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return optionalInterview;
    }

    @Override
    public List<Interview> findAllUserInterview(RequestContent requestContent) throws ServiceException {
        List<Interview> interviewList = new ArrayList<>();
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        UserInfo userInfo = (UserInfo) requestContent.getSessionAttribute().get(AttributeName.USER);
        try {
            if (userInfo.getRole().equals(UserRoleStatus.COMPANY)) {
                interviewList = interviewDao.findInterviewByCompanyId(userInfo.getId());
            } else if (userInfo.getRole().equals(UserRoleStatus.WORKER)) {
                interviewList = interviewDao.findInterviewByWorkerId(userInfo.getId());
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return interviewList;
    }
}
