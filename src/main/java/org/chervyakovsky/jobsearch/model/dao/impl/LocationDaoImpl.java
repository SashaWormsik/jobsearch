package org.chervyakovsky.jobsearch.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.LocationDao;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.mapper.impl.LocationMapperFromDbToEntity;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.sql.*;
import java.util.Optional;

/**
 * The type LocationDaoImpl class executes requests to the DB. Singleton.
 */
public class LocationDaoImpl implements LocationDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_LOCATION_BY_ID =
            "SELECT l_location_id, l_country, l_city FROM location WHERE l_location_id = ?";
    private static final String SELECT_ID_LOCATION =
            "SELECT l_location_id, l_country, l_city FROM location WHERE l_country = ? AND l_city = ?";
    private static final String INSERT_NEW_LOCATION =
            "INSERT INTO location(l_country, l_city) VALUES (?, ?)";

    private static LocationDaoImpl instance;

    private LocationDaoImpl() {
    }

    public static LocationDaoImpl getInstance() {
        if (instance == null) {
            instance = new LocationDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<Long> save(Location location) throws DaoException {
        Optional<Long> optionalId = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_LOCATION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, location.getCountry());
            statement.setString(2, location.getCity());
            int row = statement.executeUpdate();
            if (row == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    long locationId = resultSet.getLong(1);
                    optionalId = Optional.of(locationId);
                }
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalId;
    }

    @Override
    public Optional<Location> locationIsPresent(Location location) throws DaoException {
        Optional<Location> optionalLocation = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ID_LOCATION)) {
            statement.setString(1, location.getCountry());
            statement.setString(2, location.getCity());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MapperFromDbToEntity<Location> mapper = new LocationMapperFromDbToEntity();
                optionalLocation = mapper.map(resultSet);
            }
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalLocation;
    }

    @Override
    public Optional<Location> findById(long id) throws DaoException {
        Optional<Location> optionalLocation = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOCATION_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MapperFromDbToEntity<Location> mapper = new LocationMapperFromDbToEntity();
                optionalLocation = mapper.map(resultSet);
            }
        } catch (SQLException | DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalLocation;
    }

    @Override
    public boolean insert(Location location) {
        throw new UnsupportedOperationException("method is not supported");
    }

    @Override
    public boolean update(Location location) {
        throw new UnsupportedOperationException("method is not supported");
    }
}
