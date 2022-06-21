package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.Optional;

public interface LocationService {
    Optional<Location> save(RequestContent requestContent) throws ServiceException;

    Optional<Location> findUserLocation(UserInfo userInfo) throws ServiceException;

    Optional<Location> findLocationById(Long id) throws ServiceException;

    Optional<Location> locationIsPresent(RequestContent requestContent) throws ServiceException;
}
