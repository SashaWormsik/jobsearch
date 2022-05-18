package org.chervyakovsky.jobsearch.model.mapper;

import org.chervyakovsky.jobsearch.model.entity.AbstractEntity;

import java.util.Optional;

public interface CustomMapperFromRequestToEntity<E extends AbstractEntity> {

    E map (RequestContent requestContent);
}
