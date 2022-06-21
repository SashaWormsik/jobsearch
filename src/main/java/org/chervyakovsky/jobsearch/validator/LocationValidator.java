package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.regex.Pattern;

public class LocationValidator {


    // ^[A-ZА-Я][a-zа-я]+(\h?\'?\-?[A-ZА-Я]?[a-zа-я]+)*$
    private static final String COUNTRY_REGEX = "^[A-ZА-Я][a-zа-я]+(\\h?\\'?\\-?[A-ZА-Я]?[a-zа-я]+)*$";
    private static final String CITY_REGEX = "^[A-ZА-Я][a-zа-я]+(\\h?\\'?\\-?[A-ZА-Я]?[a-zа-я]+)*$";

    private static LocationValidator instance;

    private LocationValidator() {
    }

    public static LocationValidator getInstance() {
        if (instance == null) {
            instance = new LocationValidator();
        }
        return instance;
    }

    public boolean validateCountry(String country){
        return validate(country, COUNTRY_REGEX);
    }

    public boolean validateCty(String city){
        return validate(city, CITY_REGEX);
    }

    public boolean isValidLocationData(RequestContent requestContent){
        boolean result = true;
        HashMap<String, String[]> dataMap = requestContent.getRequestParameters();
        String city = getParameter(dataMap, ParameterName.LOCATION_CITY);
        String country = getParameter(dataMap, ParameterName.LOCATION_COUNTRY);
        if(!validateCty(city)){
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOCATION_CITY, true);
            result = false;
        }
        if(!validateCountry(country)){
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOCATION_COUNTRY, true);
            result = false;
        }
        return result;
    }

    private boolean validate(String validationString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (validationString == null || validationString.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(validationString).matches();
    }

    private String getParameter(HashMap<String, String[]> map, String parameterName) {
        String[] parameterValues = map.get(parameterName);
        if (parameterValues != null && parameterValues.length > 0 && parameterValues[0] != null) {
            return parameterValues[0];
        }
        return null;
    }
}
