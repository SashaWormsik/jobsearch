package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.Optional;

public interface LocationDao extends BaseDao<Location>{
    Optional<Long> save(Location location) throws DaoException;

    Optional<Location> locationIsPresent(Location location) throws DaoException;
}
