package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VacancyDao extends BaseDao<Vacancy>{

    HashMap<Vacancy, Map.Entry<Location, UserInfo>> findByCriteria(Vacancy vacancy, Location location, int offset, Integer pageCount) throws DaoException;

    boolean insertWithNewLocation(Vacancy vacancy, Location location) throws DaoException;

    boolean updateVacancyWithCreateNewLocation(Vacancy vacancy, Location location) throws DaoException;

}
