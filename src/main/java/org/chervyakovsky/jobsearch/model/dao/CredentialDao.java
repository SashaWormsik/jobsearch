package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;

import java.util.Optional;

/**
 * The interface CredentialDao.
 */
public interface CredentialDao extends BaseDao<Credential> {
    /**
     * Searches for user credentials with an active status.
     *
     * @param login the login used to identify user.
     * @return the optional credential.
     * @throws DaoException if the was any SQLException during database operations.
     */
    Optional<Credential> findActiveByLogin(String login) throws DaoException;
}
