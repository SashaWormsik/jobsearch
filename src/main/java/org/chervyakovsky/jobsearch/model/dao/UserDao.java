package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;

import java.util.Optional;

public interface UserDao extends BaseDao<UserInfo> {

    Optional<UserInfo> findUserByLogin(String login) throws DaoException;

    Optional<UserInfo> findUserByToken(String token) throws DaoException;

    boolean save(UserInfo userInfo, Credential credential) throws DaoException;

    boolean existLoginAndEmail(String login, String email) throws DaoException;

    boolean findUserByEmailAndUpdateUserToken(String email, String token) throws DaoException;

}
