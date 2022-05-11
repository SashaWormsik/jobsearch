package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> authenticate(String login, String password) throws ServiceException;
    boolean register ();
}
