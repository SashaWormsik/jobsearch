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
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.InterviewMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.VacancyMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.InterviewService;

import java.util.Map;
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
        try{
            if(interviewDao.isPresent(vacancy.getId(), userInfo.getId())){
                return false;
            }else {
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
        return false;
    }

    @Override
    public boolean changeInterviewStatus(RequestContent requestContent) throws ServiceException {
        return false;
    }

    @Override
    public Optional<Interview> findInterviewById(RequestContent requestContent) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Map<Interview, Map.Entry<Vacancy, UserInfo>> findAllUserInterview(RequestContent requestContent) throws ServiceException {
        return null;
    }
}
