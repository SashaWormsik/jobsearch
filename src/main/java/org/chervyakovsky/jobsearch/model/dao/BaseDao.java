package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.util.Optional;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public interface BaseDao<T extends AbstractEntity> {
    /**
     * Finds entity by id.
     *
     * @param id the id
     * @return the entity optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Insert new entity.
     *
     * @param t the t
     * @return {@code true} if created
     * @throws DaoException the dao exception
     */
    boolean insert(T t) throws DaoException;

    /**
     * Updates entity.
     *
     * @param t the t
     * @return the entity with old data optional
     * @throws DaoException the dao exception
     */
    boolean update(T t) throws DaoException;

}
