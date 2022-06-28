package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VacancyDao extends BaseDao<Vacancy>{

    boolean insertVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException;

    HashMap<Vacancy, Location> findVacancyForCompany(long companyId, Pageable pageable) throws DaoException;

    HashMap<Vacancy, Map.Entry<Location, UserInfo>> findByCriteria(Vacancy vacancy, Location location, Pageable pageable) throws DaoException;

    Map<Vacancy, Map.Entry<Location, UserInfo>> findVacancyById(long vacancyId) throws DaoException;

    boolean updateVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException;

}
