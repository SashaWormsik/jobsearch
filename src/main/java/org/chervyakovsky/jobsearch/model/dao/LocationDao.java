package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;

import java.util.Optional;

/**
 * The interface CredentialDao.
 */
public interface LocationDao extends BaseDao<Location> {

    /**
     * Saves the Location object in the database.
     *
     * @param location the object being saved.
     * @return the assigned Id in the database
     * @throws DaoException if the was any SQLException during database operations.
     */
    Optional<Long> save(Location location) throws DaoException;

    /**
     * Checks for the presence of this Location object in the database.
     *
     * @param location the Location object to check for
     * @return if found, returns this Location in an Optional wrapper, otherwise an empty Optional.
     * @throws DaoException if the was any SQLException during database operations.
     */
    Optional<Location> locationIsPresent(Location location) throws DaoException;
}
