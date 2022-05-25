package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.UserDao;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user_info WHERE u_login = ?";
    private static final String SELECT_EXISTS_LOGIN_EMAIL = "SELECT EXISTS (SELECT u_login FROM user_info WHERE u_login = ? OR u_email = ?)";
    private static final String SELECT_USER_BY_TOKEN = "SELECT * FROM user_info WHERE u_token = ?";
    private static final String INSERT_NEW_USER =
            "INSERT INTO user_info (u_login, u_email, u_role, u_user_status, u_user_name, u_user_surname, "
                    + "u_working_status, u_education) "
                    + "VALUES(?, ?, ?::user_role_enum, ?, ?, ?, ?::user_status_enum, ?::education_enum)";
    private static final String INSERT_NEW_CREDENTIAL =
            "INSERT INTO credential (c_password, c_active, c_create_date, c_user_info_id)" +
                    "VALUES(?, ?, ?,? )";

    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public Optional<UserInfo> findByToken(String token) throws DaoException {
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_TOKEN)) {
            statement.setString(1, token);
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
    public boolean save(UserInfo userInfo, Credential credential) throws DaoException {
        boolean resultInsert = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement userStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement credentialStatement = connection.prepareStatement(INSERT_NEW_CREDENTIAL)) {
            connection.setAutoCommit(false);
            UserRoleStatus roleStatus = userInfo.getRole();
            userStatement.setString(1, userInfo.getLogin());
            userStatement.setString(2, userInfo.getEmail());
            userStatement.setString(3, userInfo.getRole().name());
            userStatement.setBoolean(4, userInfo.getUserStatus());
            userStatement.setString(5, userInfo.getUserName());
            if (roleStatus == UserRoleStatus.WORKER || roleStatus == UserRoleStatus.ADMIN) {
                userStatement.setString(6, userInfo.getUserSurName());
            } else {
                userStatement.setNull(6, Types.OTHER, "user_role_enum");
            }
            if (roleStatus == UserRoleStatus.WORKER) {
                userStatement.setString(7, userInfo.getWorkingStatus().name());
                userStatement.setString(8, userInfo.getEducation().name());
            } else {
                userStatement.setNull(7, Types.OTHER, "user_working_status_enum");
                userStatement.setNull(8, Types.OTHER, "education_enum");
            }
            int row = userStatement.executeUpdate();
            if (row == 1) {
                ResultSet resultSet = userStatement.getGeneratedKeys();
                resultSet.next();
                long userId = resultSet.getLong(1);
                credentialStatement.setString(1, credential.getPassword());
                credentialStatement.setBoolean(2, credential.getActive());
                credentialStatement.setDate(3, new Date(credential.getCreateDate().getTime()));
                credentialStatement.setLong(4, userId);
                credentialStatement.executeUpdate();
                resultInsert = true;
            }
            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                LOGGER.log(Level.ERROR, throwables); // TODO Add comment
                throw new DaoException(throwables);  // TODO Add comment
            }
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            //throw new DaoException(exception);  // TODO Add comment
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception); // TODO Add comment
                throw new DaoException(exception);  // TODO Add comment
            }

        }
        return resultInsert;
    }

    @Override
    public boolean existLoginAndEmail(String login, String email) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXISTS_LOGIN_EMAIL)) {
            statement.setString(1, login);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean(1);
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            throw new DaoException(exception);  // TODO Add comment
        }
        return result;
    }

    @Override
    public Optional<UserInfo> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean insert(UserInfo userInfo) throws DaoException {
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

}
