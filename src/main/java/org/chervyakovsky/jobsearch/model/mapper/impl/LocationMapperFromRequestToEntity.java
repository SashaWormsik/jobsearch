package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.function.Consumer;

public class LocationMapperFromRequestToEntity implements MapperFromRequestToEntity<Location> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Location map(RequestContent requestContent) {
        Location location = new Location();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        String[] id = requestParameters.get(ParameterName.LOCATION_ID);
        String[] country = requestParameters.get(ParameterName.LOCATION_COUNTRY);
        String[] city = requestParameters.get(ParameterName.LOCATION_CITY);
        apply(id, s -> location.setId(Long.parseLong(s)));
        apply(country, location::setCountry);
        apply(city, location::setCity);
        return location;
    }

    private void apply(String[] parameterValue, Consumer<String> consumer) {
        if (parameterValue != null && parameterValue.length > 0 && parameterValue[0] != null) {
            consumer.accept(parameterValue[0]);
        }
    }
}
