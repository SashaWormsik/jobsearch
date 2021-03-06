package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromDbToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The type LocationMapperFromDbToEntity class. Maps result set to the Location class object.
 */
public class LocationMapperFromDbToEntity implements MapperFromDbToEntity<Location> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Location> map(ResultSet resultSet) throws DaoException {
        Location location = new Location();
        Optional<Location> optionalLocation;
        try {
            location.setId(resultSet.getLong(ColumnName.LOCATION_ID));
            location.setCountry(resultSet.getString(ColumnName.LOCATION_COUNTRY));
            location.setCity(resultSet.getString(ColumnName.LOCATION_CITY));
            optionalLocation = Optional.of(location);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new DaoException(exception);
        }
        return optionalLocation;
    }
}
