package org.chervyakovsky.jobsearch.model.dao;

import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;

import java.util.Optional;

public interface UserDao extends BaseDao<UserInfo>{

    Optional<UserInfo> authenticate (String login, String password);

    Optional<UserInfo> findUserByLogin(String login) throws DaoException;
}
