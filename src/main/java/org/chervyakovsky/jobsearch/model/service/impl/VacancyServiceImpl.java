package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        // TODO валидация
        /*
        сделать валидацию
        VacancyValidator vacancyValidator = VacancyValidator.getInstance();
        LocationValidator locationValidator = LocationValidator.getInstance();
        */
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        try {
            Optional<Long> locationId = locationDao.locationIsPresent(location);
            if (locationId.isPresent()) {
                vacancy.setLocationId(locationId.get());
                result = vacancyDao.insert(vacancy);
            } else {
                result = vacancyDao.insertWithNewLocation(vacancy, location);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception);
        }
        return result;
    }

    @Override
    public boolean updateVacancy(RequestContent requestContent) throws ServiceException {
        boolean result = false;
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        try {
            Optional<Long> locationId = locationDao.locationIsPresent(location);
            if (locationId.isPresent()) {
                vacancy.setLocationId(locationId.get());
                result = vacancyDao.update(vacancy);
            } else {
                result = vacancyDao.updateVacancyWithCreateNewLocation(vacancy, location);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception);
        }
        return result;
    }


    @Override
    public HashMap<Vacancy, Map.Entry<Location, UserInfo>> searchVacancyByCriteria(RequestContent requestContent, int offset, Integer pageCount) throws ServiceException {
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        MapperFromRequestToEntity<Vacancy> mapperVacancy = new VacancyMapperFromRequestToEntity();
        MapperFromRequestToEntity<Location> mapperLocation = new LocationMapperFromRequestToEntity();
        Vacancy vacancy = mapperVacancy.map(requestContent);
        Location location = mapperLocation.map(requestContent);
        HashMap<Vacancy, Map.Entry<Location, UserInfo>> result = new HashMap<>();
        try {
            result = vacancyDao.findByCriteria(vacancy, location, offset, pageCount);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new ServiceException(exception);
        }
        return result;
    }
}
