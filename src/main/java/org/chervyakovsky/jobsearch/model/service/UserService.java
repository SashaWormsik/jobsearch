package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * The interface UserService.
 */
public interface UserService {

    /**
     * Check if there is a user with the specified username or email address.
     *
     * @param requestContent must contain values for user
     * @return boolean. True if user with such login or email exists, otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean existLoginOrEmail(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for users according to the specified criteria.
     *
     * @param requestContent must contain values for user
     * @return the list of found users
     * @throws ServiceException the service exception.
     */
    List<UserInfo> findAllUserByCriteria(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for all users
     *
     * @param requestContent must contain values for user
     * @param pageable       object for pagination
     * @return the list of found users
     * @throws ServiceException the service exception.
     */
    List<UserInfo> findAllUser(RequestContent requestContent, Pageable pageable) throws ServiceException;

    /**
     * Authenticates user.
     *
     * @param requestContent must contain values for user
     * @return optional user
     * @throws ServiceException the service exception.
     */
    Optional<UserInfo> authenticate(RequestContent requestContent) throws ServiceException;

    /**
     * Registration new user.
     *
     * @param requestContent must contain values for user
     * @return boolean. True if the user is registered, otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean registrationNewUser(RequestContent requestContent) throws ServiceException;

    /**
     * Activates the user's account
     *
     * @param requestContent must contain values for user
     * @return boolean. True if the user is activated otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean activateUserAccount(RequestContent requestContent) throws ServiceException;

    /**
     * Sends an email when the password is changed
     *
     * @param requestContent must contain values for user
     * @return boolean. True if the email has been sent, otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean sendEmailToRecoverPassword(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for a user by token
     *
     * @param requestContent must contain values for user
     * @return optional user
     * @throws ServiceException the service exception.
     */
    Optional<UserInfo> findUserByToken(RequestContent requestContent) throws ServiceException;

    /**
     * Searches for a user by шв
     *
     * @param requestContent must contain values for user
     * @return optional user
     * @throws ServiceException the service exception.
     */
    Optional<UserInfo> findUserById(RequestContent requestContent) throws ServiceException;

    /**
     * Updates the user
     *
     * @param userInfo must contain values for user
     * @return boolean. Is true if the update is successful, otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean updateUser(UserInfo userInfo) throws ServiceException;

    /**
     * Updates the user
     *
     * @param userInfo       user with new information
     * @param requestContent must contain values for user
     * @return boolean. Is true if the update is successful, otherwise false.
     * @throws ServiceException the service exception.
     */
    boolean updateUser(UserInfo userInfo, RequestContent requestContent) throws ServiceException;

    /**
     * Changes the user status
     *
     * @param requestContent must contain values for user
     * @return the boolean {@code true}  if the status has been changed.
     * @throws ServiceException the service exception.
     */
    boolean changeUserStatus(RequestContent requestContent) throws ServiceException;
}
