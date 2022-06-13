package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> authenticate(RequestContent requestContent) throws ServiceException;
    boolean registrationNewUser(RequestContent requestContent) throws ServiceException;
    boolean activateUserAccount(RequestContent requestContent) throws ServiceException;
    boolean sendEmailToRecoverPassword(RequestContent requestContent) throws ServiceException;
    Optional<UserInfo> findUserByToken(RequestContent requestContent) throws ServiceException;
    Optional<UserInfo> findUserById(RequestContent requestContent) throws ServiceException;
    boolean updateUser(UserInfo userInfo)throws ServiceException;
    boolean updateUser(UserInfo userInfo, RequestContent requestContent)throws ServiceException;
}
