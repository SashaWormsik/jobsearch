package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
 * The interface VacancyDao.
 */
public interface VacancyDao extends BaseDao<Vacancy> {

    /**
     * Saves the vacancy and the specified location.
     *
     * @param vacancy  saved vacancy
     * @param location saved location
     * @return boolean. Is true if the insertion is successful, otherwise false.
     * @throws DaoException if the was any SQLException during database operations.
     */
    boolean insertVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException;

    /**
     * Searches for all vacancies for this company.
     *
     * @param companyId id of the specified company.
     * @param pageable  object for pagination
     * @return a map where the key is the vacancy and the value is the vacancy location.
     * @throws DaoException if the was any SQLException during database operations.
     */
    HashMap<Vacancy, Location> findVacancyForCompany(long companyId, Pageable pageable) throws DaoException;

    /**
     * Searches for vacancies by criterion.
     *
     * @param vacancy stores part of the search query
     * @param location stores part of the search query
     * @param pageable object for pagination
     * @return Returns a map where the key is a vacancy, and the value is Map.Entry with the location is a key and the company is a value for this vacancy.
     * @throws DaoException if the was any SQLException during database operations.
     */
    HashMap<Vacancy, Map.Entry<Location, UserInfo>> findByCriteria(Vacancy vacancy, Location location, Pageable pageable) throws DaoException;

    /**
     * Searches for vacancies by Id.
     *
     * @param vacancyId vacancy id
     * @return Returns a map where the key is a vacancy, and the value is Map.Entry with the location is a key and the company is a value for this vacancy.
     * @throws DaoException if the was any SQLException during database operations.
     */
    Map<Vacancy, Map.Entry<Location, UserInfo>> findVacancyById(long vacancyId) throws DaoException;

    /**
     * Updates the vacancy.
     *
     * @param vacancy  vacancy with new values
     * @param location location for the updated vacancy
     * @return boolean. Is true if the update is successful, otherwise false.
     * @throws DaoException if the was any SQLException during database operations.
     */
    boolean updateVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException;

}
