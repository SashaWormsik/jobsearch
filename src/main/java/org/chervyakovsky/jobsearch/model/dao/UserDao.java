package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * The interface UserDao.
 */
public interface UserDao extends BaseDao<UserInfo> {
    /**
     * Searches for a user by login
     *
     * @param login the login used to identify user.
     * @return the optional user
     * @throws DaoException if the was any SQLException during database operations.
     */
    Optional<UserInfo> findUserByLogin(String login) throws DaoException;

    /**
     * Searches for users according to the specified criteria.
     *
     * @param criteria       the specified criteria
     * @param userRoleStatus the role of the users for which the search is performed
     * @return the list of found users according to the role
     * @throws DaoException if the was any SQLException during database operations.
     */
    List<UserInfo> findAllByCriteria(String criteria, UserRoleStatus userRoleStatus) throws DaoException;

    /**
     * Searches for all users by the specified role.
     *
     * @param roleStatus the role of the users for which the search is performed
     * @param pageable   object for pagination
     * @return the list of found users according to the role
     * @throws DaoException if the was any SQLException during database operations.
     */
    List<UserInfo> findAll(UserRoleStatus roleStatus, Pageable pageable) throws DaoException;

    /**
     * Searches for a user by token.
     *
     * @param token the token used to identify user.
     * @return the optional user
     * @throws DaoException if the was any SQLException during database operations.
     */
    Optional<UserInfo> findUserByToken(String token) throws DaoException;

    /**
     * Saves the user and their credentials
     *
     * @param userInfo   saved user
     * @param credential saved user credentials
     * @return boolean. Is true if the insertion is successful, otherwise false.
     * @throws DaoException if the was any SQLException during database operations.
     */
    boolean save(UserInfo userInfo, Credential credential) throws DaoException;

    /**
     * Check if there is a user with the specified username or email address.
     *
     * @param login the user login
     * @param email the user email
     * @return boolean. True if user with such login or email exists, otherwise false.
     * @throws DaoException if the was any SQLException during database operations.
     */
    boolean existLoginAndEmail(String login, String email) throws DaoException;

    /**
     * Updates the user's token by the specified mail
     *
     * @param email the user email
     * @param token new token
     * @return boolean. Is true if the update is successful, otherwise false.
     * @throws DaoException
     */
    boolean updateUserTokenByEmail(String email, String token) throws DaoException;

}
