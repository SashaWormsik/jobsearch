package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

public interface CredentialService {
    boolean updatePasswordByUserId(RequestContent requestContent, Long userId) throws ServiceException;
}
