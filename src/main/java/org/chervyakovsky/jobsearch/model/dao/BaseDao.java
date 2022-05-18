package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {

    Optional<T> findById(long id) throws DaoException;

    boolean insert(T t);

    boolean delete(T t);

    T update(T t);

    List<T> findAll(T t);

    T update(long id, T t);
}