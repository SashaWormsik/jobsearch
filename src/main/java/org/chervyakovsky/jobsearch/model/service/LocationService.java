package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.Optional;

/**
 * The interface LocationService.
 */
public interface LocationService {
    /**
     * Saves the location.
     *
     * @param requestContent must contain values for location
     * @return the boolean {@code true} if created
     * @throws ServiceException the service exception.
     */
    Optional<Location> save(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for a location for a given user.
     *
     * @param userInfo user
     * @return interview optional
     * @throws ServiceException the service exception.
     */
    Optional<Location> findUserLocation(UserInfo userInfo) throws ServiceException;

    /**
     * Checks if the location exists.
     *
     * @param requestContent must contain values for location
     * @return interview optional
     * @throws ServiceException the service exception.
     */
    Optional<Location> locationIsPresent(RequestContent requestContent) throws ServiceException;
}
