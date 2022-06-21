package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<UserInfo> {

    Optional<UserInfo> findUserByLogin(String login) throws DaoException;

    List<UserInfo> findAllByCriteria(String criteria, UserRoleStatus userRoleStatus) throws DaoException;

    List<UserInfo> findAll(UserRoleStatus roleStatus, Pageable pageable) throws DaoException;

    Optional<UserInfo> findUserByToken(String token) throws DaoException;

    boolean save(UserInfo userInfo, Credential credential) throws DaoException;

    boolean existLoginAndEmail(String login, String email) throws DaoException;

    boolean updateUserTokenByEmail(String email, String token) throws DaoException;

}
