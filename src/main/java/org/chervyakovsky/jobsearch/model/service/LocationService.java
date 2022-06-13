package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.Optional;

public interface LocationService {
    Optional<Location> findUserLocation(UserInfo userInfo) throws ServiceException;

    Optional<Location> findLocationById(Long id) throws ServiceException;

    Optional<Long> locationIsPresent(RequestContent requestContent) throws ServiceException;
}
