package org.chervyakovsky.jobsearch.validator;

import java.util.regex.Pattern;

public class LocationValidator {

    private static final String COUNTRY_REGEX = "^[p\\{Alpha}А-Яа-я]+[-\\s]?[p\\{Alpha}А-Яа-я]+{1, 20}"; // TODO
    private static final String CITY_REGEX = "^[p\\{Alpha}А-Яа-я]+[-\\s]?[p\\{Alpha}А-Яа-я]+{1, 20}"; // TODO

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


    private boolean validate(String validationString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (validationString == null || validationString.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(validationString).matches();
    }
}
