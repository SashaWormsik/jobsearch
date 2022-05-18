package org.chervyakovsky.jobsearch.model.mapper;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface CustomMapperFromDbToEntity<E extends AbstractEntity>{

    Optional<E> map(ResultSet resultSet) throws DaoException;
}
