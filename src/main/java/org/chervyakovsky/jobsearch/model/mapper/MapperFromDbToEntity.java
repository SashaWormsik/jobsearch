package org.chervyakovsky.jobsearch.model.mapper;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * The interface MapperFromDbToEntity.
 *
 * @param <E> the type of the entity to map to.
 */
public interface MapperFromDbToEntity<E extends AbstractEntity> {
    /**
     * Maps the given result set to the entity.
     *
     * @param resultSet the result set
     * @return the entity optional
     * @throws DaoException - if the was any SQLException during database operations.
     */
    Optional<E> map(ResultSet resultSet) throws DaoException;
}
