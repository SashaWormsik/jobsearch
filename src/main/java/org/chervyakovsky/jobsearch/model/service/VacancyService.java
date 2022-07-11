package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
 * The interface VacancyService.
 */
public interface VacancyService {

    /**
     * Saves a new vacancy
     *
     * @param requestContent must contain values for vacancy
     * @return the boolean {@code true} if created
     * @throws ServiceException the service exception.
     */
    boolean createNewVacancy(RequestContent requestContent) throws ServiceException;

    /**
     * Updates the vacancy
     *
     * @param requestContent must contain values for vacancy
     * @return the boolean {@code true} if updated
     * @throws ServiceException the service exception.
     */
    boolean updateVacancy(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for vacancies by Id.
     *
     * @param requestContent must contain values for vacancy
     * @return Returns a map where the key is a vacancy, and the value is Map.Entry with the location is a key and the company is a value for this vacancy.
     * @throws ServiceException the service exception.
     */
    Map<Vacancy, Map.Entry<Location, UserInfo>> findVacancyById(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for vacancies by criterion.
     *
     * @param requestContent must contain values for vacancy
     * @param pageable       object for pagination
     * @return Returns a map where the key is a vacancy, and the value is Map.Entry with the location is a key and the company is a value for this vacancy.
     * @throws ServiceException the service exception.
     */
    Map<Vacancy, Map.Entry<Location, UserInfo>> searchVacancyByCriteria(RequestContent requestContent, Pageable pageable) throws ServiceException;

    /**
     * Searches for all vacancies for this company.
     *
     * @param requestContent must contain values for vacancy
     * @param pageable       object for pagination
     * @return a map where the key is the vacancy and the value is the vacancy location.
     * @throws ServiceException the service exception.
     */
    Map<Vacancy, Location> searchVacanciesForCompany(RequestContent requestContent, Pageable pageable) throws ServiceException;

    /**
     * Updates the vacancy status
     *
     * @param requestContent must contain values for vacancy
     * @return the boolean {@code true}  if the status has been changed.
     * @throws ServiceException the service exception.
     */
    boolean changeVacancyStatus(RequestContent requestContent) throws ServiceException;
}
