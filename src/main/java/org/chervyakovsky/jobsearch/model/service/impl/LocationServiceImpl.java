package org.chervyakovsky.jobsearch.model.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.dao.LocationDao;
import org.chervyakovsky.jobsearch.model.dao.impl.LocationDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.mapper.impl.LocationMapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.service.LocationService;
import org.chervyakovsky.jobsearch.validator.LocationValidator;

import java.util.Optional;

public class LocationServiceImpl implements LocationService {
    private static final Logger LOGGER = LogManager.getLogger();

    private static LocationServiceImpl instance;

    private LocationServiceImpl() {
    }

    public static LocationServiceImpl getInstance() {
        if (instance == null) {
            instance = new LocationServiceImpl();
        }
        return instance;
    }


    @Override
    public Optional<Location> save(RequestContent requestContent) throws ServiceException {
        LocationValidator validator = LocationValidator.getInstance();
        if (!validator.isValidLocationData(requestContent)) {
            return Optional.empty();
        }
        Optional<Location> optionalLocation = Optional.empty();
        LocationMapperFromRequestToEntity mapper = new LocationMapperFromRequestToEntity();
        Location location = mapper.map(requestContent);
        LocationDao locationDao = LocationDaoImpl.getInstance();
        try {
           long id = locationDao.save(location).orElse(Long.MIN_VALUE);
           optionalLocation = locationDao.findById(id);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return optionalLocation;
    }

    @Override
    public Optional<Location> findUserLocation(UserInfo userInfo) throws ServiceException {
        Long locationUserId = userInfo.getLocationId();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        Optional<Location> optionalLocation = Optional.empty();
        try {
            if (locationUserId != null) {
                optionalLocation = locationDao.findById(locationUserId);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return optionalLocation;
    }

    @Override
    public Optional<Location> locationIsPresent(RequestContent requestContent) throws ServiceException {
        LocationValidator validator = LocationValidator.getInstance();
        if (!validator.isValidLocationData(requestContent)) {
            return Optional.empty();
        }
        Optional<Location> optionalLocation = Optional.empty();
        LocationMapperFromRequestToEntity mapper = new LocationMapperFromRequestToEntity();
        Location location = mapper.map(requestContent);
        LocationDao locationDao = LocationDaoImpl.getInstance();
        try {
            optionalLocation = locationDao.locationIsPresent(location);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException(exception);
        }
        return optionalLocation;
    }
}



