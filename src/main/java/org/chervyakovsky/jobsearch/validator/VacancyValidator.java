package org.chervyakovsky.jobsearch.validator;

import java.util.regex.Pattern;

public class VacancyValidator {

    private static final String JOB_TITLE_REGEX = "";
    private static final String SALARY_REGEX = "";
    private static final String CURRENCY_REGEX = "";
    private static final String RESPONSIBILITY_REGEX = "";
    private static final String REQUIREMENT_REGEX = "";
    private static final String WORK_CONDITION_REGEX = "";

    private static VacancyValidator instance;

    private VacancyValidator() {
    }

    public static VacancyValidator getInstance() {
        if (instance == null) {
            instance = new VacancyValidator();
        }
        return instance;
    }

    public boolean validateCountry(String vacancy) {
        return validate(vacancy, JOB_TITLE_REGEX);
    }

    private boolean validate(String validationString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (validationString == null || validationString.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(validationString).matches();
    }
}
