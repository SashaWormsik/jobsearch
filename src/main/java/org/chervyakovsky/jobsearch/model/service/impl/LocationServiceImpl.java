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
import org.chervyakovsky.jobsearch.model.service.LocationService;

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
    public Optional<Location> findUserLocation(UserInfo userInfo) throws ServiceException {
        Long locationUserId = userInfo.getLocationId();
        LocationDao locationDao = LocationDaoImpl.getInstance();
        Optional<Location> optionalLocation = Optional.empty();
        try {
            if (locationUserId != null) {
                optionalLocation = locationDao.findById(locationUserId);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return optionalLocation;
    }

    @Override
    public Optional<Location> findLocationById(Long id) throws ServiceException {
        LocationDao locationDao = LocationDaoImpl.getInstance();
        Optional<Location> optionalLocation = Optional.empty();
        try {
            if (id != null) {
                optionalLocation = locationDao.findById(id);
            }
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new ServiceException(exception); // TODO
        }
        return optionalLocation;
    }

    @Override
    public Optional<Long> locationIsPresent(RequestContent requestContent) throws ServiceException {
        LocationDao locationDao = LocationDaoImpl.getInstance();
        Optional<Long> longOptional = Optional.empty();

        return longOptional;
    }
}



