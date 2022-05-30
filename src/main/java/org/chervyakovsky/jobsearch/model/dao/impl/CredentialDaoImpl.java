package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.CredentialDao;
import org.chervyakovsky.jobsearch.model.entity.Credential;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.CredentialMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CredentialDaoImpl implements CredentialDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_ACTIVE_CREDENTIAL_BY_USER_ID = "SELECT * FROM credential WHERE c_user_info_id = ? AND c_active = true";
    private static final String SELECT_ACTIVE_CREDENTIAL_BY_LOGIN =
            "SELECT c_credential_id, c_password, c_active, c_create_date, c_user_info_id " +
                    "FROM credential " +
                    "JOIN user_info ON c_user_info_id = u_user_info_id " +
                    "WHERE u_login = ? AND c_active = 'true'";
    private static final String INSERT_NEW_CREDENTIAL =
            "INSERT INTO credential (c_password, c_active, c_create_date, c_user_info_id)" +
                    "VALUES(?, ?, ?,? )";
    private static final String UPDATE_OLD_CREDENTIAL_BY_USER_ID =
            "UPDATE credential SET c_active = 'false' WHERE c_user_info_id = ?";

    private static CredentialDaoImpl instance;

    private CredentialDaoImpl() {
    }

    public static CredentialDaoImpl getInstance() {
        if (instance == null) {
            instance = new CredentialDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean update(Credential credential) throws DaoException {
        boolean result = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement updateOldStatement = connection.prepareStatement(UPDATE_OLD_CREDENTIAL_BY_USER_ID);
             PreparedStatement insertNewStatement = connection.prepareStatement(INSERT_NEW_CREDENTIAL)) {
            connection.setAutoCommit(false);
            updateOldStatement.setLong(1, credential.getUserInfoId());
            int rowOld = updateOldStatement.executeUpdate();
            insertNewStatement.setString(1, credential.getPassword());
            insertNewStatement.setBoolean(2, credential.getActive());
            insertNewStatement.setDate(3, new Date(credential.getCreateDate().getTime()));
            insertNewStatement.setLong(4, credential.getUserInfoId());
            int rowInsert = insertNewStatement.executeUpdate();
            connection.commit();
            if (rowInsert != 0 && rowOld !=0) {
                result = true;
            }else {
                connection.rollback();
            }
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOGGER.log(Level.ERROR, sqlException); // TODO Add comment
                throw new DaoException(sqlException);  // TODO Add comment
            }
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            throw new DaoException(exception);  // TODO Add comment
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, exception); // TODO Add comment
                throw new DaoException(exception);  // TODO Add comment
            }
        }
        return result;
    }

    @Override
    public Optional<Credential> findActiveByLogin(String login) throws DaoException {
        Optional<Credential> optionalCredential = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVE_CREDENTIAL_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CustomMapperFromDbToEntity<Credential> mapper = new CredentialMapperFromDbToEntity();
                optionalCredential = mapper.map(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            throw new DaoException(exception);  // TODO Add comment
        }
        return optionalCredential;
    }

    @Override
    public Optional<Credential> findById(long id) throws DaoException {
        Optional<Credential> optionalCredential = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVE_CREDENTIAL_BY_USER_ID); // TODO
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CustomMapperFromDbToEntity<Credential> mapper = new CredentialMapperFromDbToEntity();
                optionalCredential = mapper.map(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO Add comment
            throw new DaoException(exception);  // TODO Add comment
        }
        return optionalCredential;
    }


    @Override
    public boolean insert(Credential credential) {
        return false;
    }

    @Override
    public boolean delete(Credential credential) {
        return false;
    }


    @Override
    public List<Credential> findAll() {
        return null;
    }

}
