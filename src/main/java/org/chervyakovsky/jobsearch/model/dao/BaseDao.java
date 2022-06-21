package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {

    Optional<T> findById(long id) throws DaoException;

    boolean insert(T t) throws DaoException;

    boolean delete(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean update(T t) throws DaoException;

}
