package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean existLoginOrEmail(RequestContent requestContent) throws ServiceException;

    List<UserInfo> findAllUserByCriteria(RequestContent requestContent) throws ServiceException;

    List<UserInfo> findAllUser(RequestContent requestContent, Pageable pageable) throws ServiceException;

    Optional<UserInfo> authenticate(RequestContent requestContent) throws ServiceException;

    boolean registrationNewUser(RequestContent requestContent) throws ServiceException;

    boolean activateUserAccount(RequestContent requestContent) throws ServiceException;

    boolean sendEmailToRecoverPassword(RequestContent requestContent) throws ServiceException;

    Optional<UserInfo> findUserByToken(RequestContent requestContent) throws ServiceException;

    Optional<UserInfo> findUserById(RequestContent requestContent) throws ServiceException;

    boolean updateUser(UserInfo userInfo) throws ServiceException;

    boolean updateUser(UserInfo userInfo, RequestContent requestContent) throws ServiceException;

    boolean changeUserStatus(RequestContent requestContent) throws ServiceException;
}
