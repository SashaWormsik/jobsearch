package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.UserDao;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user_info WHERE u_login = ?";

    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<UserInfo> findUserByLogin(String login) throws DaoException {
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CustomMapperFromDbToEntity<UserInfo> mapper = new UserInfoMapperFromDbToEntity();
                optionalUserInfo = mapper.map(resultSet);
            }
        } catch (SQLException | DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            throw new DaoException(exception);  // TODO Add comment
        }
        return optionalUserInfo;
    }

    @Override
    public Optional<UserInfo> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean insert(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean delete(UserInfo userInfo) {
        return false;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> findAll(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo update(long id, UserInfo userInfo) {
        return null;
    }

}
