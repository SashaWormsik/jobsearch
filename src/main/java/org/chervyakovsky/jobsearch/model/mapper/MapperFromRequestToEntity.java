package org.chervyakovsky.jobsearch.model.mapper;

import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

/**
 * The interface MapperFromRequestToEntity.
 *
 * @param <E> the type of the entity to map to.
 */
public interface MapperFromRequestToEntity<E extends AbstractEntity> {
    /**
     * Maps data from the {@link RequestContent} object to an entity.
     *
     * @param requestContent the requestContent object with a set of appropriate parameters.
     * @return the type of the object corresponding to the type <E>.
     */
    E map(RequestContent requestContent);
}
