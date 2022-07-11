package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

/**
 * The interface CredentialService.
 */
public interface CredentialService {
    /**
     * Updates the user's password.
     *
     * @param requestContent must contain values for credentials
     * @param userId         user id
     * @return true, if the password was updated.
     * @throws ServiceException the service exception.
     */
    boolean updatePasswordByUserId(RequestContent requestContent, Long userId) throws ServiceException;
}
