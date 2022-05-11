package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.mapper.ColumnName;
import org.chervyakovsky.jobsearch.model.mapper.CustomMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LocationMapper implements CustomMapper<Location> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Location> map(ResultSet resultSet) throws DaoException {
        Location location = new Location();
        Optional<Location> optionalLocation = Optional.empty();
        try {
            location.setId(resultSet.getLong(ColumnName.LOCATION_ID));
            location.setCountry(resultSet.getString(ColumnName.LOCATION_COUNTRY));
            location.setCity(resultSet.getString(ColumnName.LOCATION_CITY));
            optionalLocation = Optional.of(location);
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO add comment
            throw new DaoException(exception); // TODO add comment
        }
        return optionalLocation;
    }
}
