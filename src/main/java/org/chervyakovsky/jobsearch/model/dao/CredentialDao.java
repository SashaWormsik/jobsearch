package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;

import java.util.Optional;

public interface CredentialDao extends BaseDao<Credential> {
    Optional<Credential> findActiveByLogin(String login) throws DaoException;
}
