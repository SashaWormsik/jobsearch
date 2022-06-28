package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.LocationDao;
import org.chervyakovsky.jobsearch.model.dao.VacancyDao;
import org.chervyakovsky.jobsearch.model.dao.impl.LocationDaoImpl;
import org.chervyakovsky.jobsearch.model.dao.impl.VacancyDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.LocationMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.VacancyMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.util.Pageable;
import org.chervyakovsky.jobsearch.validator.LocationValidator;
import org.chervyakovsky.jobsearch.validator.VacancyValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VacancyServiceImpl implements VacancyService {

    private static final Logger LOGGER = LogManager.getLogger();


    private static VacancyServiceImpl instance;

    private VacancyServiceImpl() {
    }

    public static VacancyServiceImpl getInstance() {
        if (instance == null) {
            instance = new VacancyServiceImpl();
        }
        return instance;
    }



    @Override
    public boolean createNewVacancy(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        VacancyValidator vacancyValidator = VacancyValidator.getInstance();
        LocationValidator locationValidator = LocationValidator.getInstance();
        if (!vacancyValidator.isValidVacancyData(requestContent) | !locationValidator.isValidLocationData(requestContent)) {
            return false;
        }
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        try {
            Optional<Location> optionalLocation = locationDao.locationIsPresent(location);
            if (optionalLocation.isPresent()) {
                vacancy.setLocationId(optionalLocation.get().getId());
                result = vacancyDao.insert(vacancy);
            } else {
                result = vacancyDao.insertVacancyWithCreateNewLocation(vacancy, location);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public boolean updateVacancy(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        VacancyValidator vacancyValidator = VacancyValidator.getInstance();
        LocationValidator locationValidator = LocationValidator.getInstance();
        if (!vacancyValidator.isValidVacancyData(requestContent) | !locationValidator.isValidLocationData(requestContent)) {
            return false;
        }
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        try {
            Optional<Location> optionalLocation = locationDao.locationIsPresent(location);
            if (optionalLocation.isPresent()) {
                vacancy.setLocationId(optionalLocation.get().getId());
                result = vacancyDao.update(vacancy);
            } else {
                result = vacancyDao.updateVacancyWithCreateNewLocation(vacancy, location);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public Map<Vacancy, Map.Entry<Location, UserInfo>> findVacancyById(RequestContent requestContent) throws ServiceException {
        Map<Vacancy, Map.Entry<Location, UserInfo>> result = new HashMap<>();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        try {
            result = vacancyDao.findVacancyById(vacancy.getId());
        }catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }


    @Override
    public HashMap<Vacancy, Map.Entry<Location, UserInfo>> searchVacancyByCriteria(RequestContent requestContent, Pageable pageable) throws ServiceException {
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        HashMap<Vacancy, Map.Entry<Location, UserInfo>> result = new HashMap<>();
        try {
            result = vacancyDao.findByCriteria(vacancy, location, pageable);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public Map<Vacancy, Location> searchVacanciesForCompany(RequestContent requestContent, Pageable pageable) throws ServiceException {
        String stringPage = getParameter(requestContent, ParameterName.PAGE);
        int page = stringPage == null ? pageable.getPage() : Integer.parseInt(stringPage);
        pageable.setPage(page);
        Map<String, Object> sessionAttribute = requestContent.getSessionAttribute();
        UserInfo userInfo = (UserInfo) sessionAttribute.get(AttributeName.USER);
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        Map<Vacancy, Location> result = new HashMap<>();
        try {
            result = vacancyDao.findVacancyForCompany(userInfo.getId(), pageable);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public boolean changeVacancyStatus(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        long vacancyId = vacancy.getId();
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        try {
            Optional<Vacancy> optionalVacancy = vacancyDao.findById(vacancyId);
            if(optionalVacancy.isPresent()){
                vacancy = optionalVacancy.get();
                boolean vacancyStatus = vacancy.getVacancyStatus();
                vacancy.setVacancyStatus(!vacancyStatus);
                result = vacancyDao.update(vacancy);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return result;
    }

    private String getParameter(RequestContent requestContent, String parameter) {
        String[] parameters = requestContent.getRequestParameters().get(parameter);
        if (parameters != null && parameters.length > 0 && parameters[0] != null) {
            return parameters[0];
        }
        return null;
    }
}
