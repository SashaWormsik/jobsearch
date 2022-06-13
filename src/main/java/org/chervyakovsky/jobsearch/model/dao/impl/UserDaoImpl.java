package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.UserDao;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.UserInfoMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_USER_BY_ID = "SELECT * FROM user_info WHERE u_user_info_id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user_info WHERE u_login = ?";
    private static final String SELECT_USER_BY_TOKEN = "SELECT * FROM user_info WHERE u_user_token = ?";
    private static final String SELECT_EXISTS_LOGIN_EMAIL = "SELECT EXISTS (SELECT u_login FROM user_info WHERE u_login = ? OR u_email = ?)";
    private static final String UPDATE_USER_TOKEN_WHERE_EMAIL = "UPDATE user_info SET u_user_token = ? WHERE u_email = ?";
    private static final String UPDATE_USER =
            "UPDATE user_info " +
                    "SET  u_user_status = ?, u_location_id = ?, u_user_name = ?, u_user_surname = ?, u_working_status = ?, " +
                    "u_education = ?, u_profession = ?, u_description = ?, u_user_token = ? " +
                    "WHERE u_user_info_id=?";
    private static final String INSERT_NEW_USER =
            "INSERT INTO user_info (u_login, u_email, u_role, u_user_status, u_user_name, u_user_surname, " +
                    "u_working_status, u_education, u_user_token) " +
                    "VALUES(?, ?, ?::user_role_enum, ?, ?, ?, ?::user_status_enum, ?::education_enum, ?)";
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

    @Override
    public Optional<UserInfo> findUserByToken(String token) throws DaoException {
        return getUserInfo(token, SELECT_USER_BY_TOKEN);
    }

    @Override
    public Optional<UserInfo> findUserByLogin(String login) throws DaoException {
        return getUserInfo(login, SELECT_USER_BY_LOGIN);
    }

    @Override
    public Optional<UserInfo> findById(long id) throws DaoException {
        return getUserInfo(String.valueOf(id), SELECT_USER_BY_ID);
    }

    @Override
    public boolean update(UserInfo userInfo) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setBoolean(1, userInfo.getUserStatus());
            statement.setObject(2, userInfo.getLocationId(), Types.BIGINT);
            statement.setString(3, userInfo.getUserName());
            statement.setString(4, userInfo.getUserSurName());
            statement.setObject(5, userInfo.getWorkingStatus(), Types.OTHER);
            statement.setObject(6, userInfo.getEducation(), Types.OTHER);
            statement.setString(7, userInfo.getProfession());
            statement.setString(8, userInfo.getDescription());
            statement.setString(9, userInfo.getUserToken());
            statement.setLong(10, userInfo.getId());
            int row = statement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new DaoException(exception); // TODO
        }
        return result;
    }

    @Override
    public boolean save(UserInfo userInfo, Credential credential) throws DaoException {
        boolean resultInsert = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        int rowCredential = 0;
        int rowUser = 0;
        try (PreparedStatement userStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement credentialStatement = connection.prepareStatement(INSERT_NEW_CREDENTIAL)) {
            connection.setAutoCommit(false);
            userStatement.setString(1, userInfo.getLogin());
            userStatement.setString(2, userInfo.getEmail());
            userStatement.setObject(3, userInfo.getRole(), Types.OTHER);
            userStatement.setBoolean(4, userInfo.getUserStatus());
            userStatement.setString(5, userInfo.getUserName());
            userStatement.setString(6, userInfo.getUserSurName());
            userStatement.setObject(7, userInfo.getWorkingStatus(), Types.OTHER);
            userStatement.setObject(8, userInfo.getEducation(), Types.OTHER);
            userStatement.setString(9, userInfo.getUserToken());
            rowUser = userStatement.executeUpdate();
            if (rowUser == 1) {
                ResultSet resultSet = userStatement.getGeneratedKeys();
                resultSet.next();
                long userId = resultSet.getLong(1);
                credentialStatement.setString(1, credential.getPassword());
                credentialStatement.setBoolean(2, credential.getActive());
                credentialStatement.setDate(3, new Date(credential.getCreateDate().getTime()));
                credentialStatement.setLong(4, userId);
                rowCredential = credentialStatement.executeUpdate();
            }
            if (rowCredential == 1) {
                connection.commit();
                resultInsert = true;
            }else {
                connection.rollback();
            }
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOGGER.log(Level.ERROR, sqlException); // TODO
                throw new DaoException(sqlException);  // TODO
            }
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new DaoException(exception);  // TODO
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception); // TODO
                throw new DaoException(exception);  // TODO
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
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new DaoException(exception);  // TODO
        }
        return result;
    }

    @Override
    public boolean findUserByEmailAndUpdateUserToken(String email, String token) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TOKEN_WHERE_EMAIL)) {
            statement.setString(1, token);
            statement.setString(2, email);
            int row = statement.executeUpdate();
            if (row != 0) {
                result = true;
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new DaoException(exception); // TODO
        }
        return result;
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
    public List<UserInfo> findAll() {
        return null;
    }

    private Optional<UserInfo> getUserInfo(String columnName, String queryString) throws DaoException {
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setString(1, columnName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MapperFromDbToEntity<UserInfo> mapper = new UserInfoMapperFromDbToEntity();
                optionalUserInfo = mapper.map(resultSet);
            }
        } catch (SQLException | DaoException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new DaoException(exception);  // TODO
        }
        return optionalUserInfo;
    }
}
