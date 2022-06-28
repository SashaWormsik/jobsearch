package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyValidator {

    private static final String JOB_TITLE_REGEX = "^([А-Яа-яa-zA-Z]{1})([\\hа-яa-z-]+)([а-яa-z]{1})$";
    private static final String SALARY_REGEX = "^[1-9][\\d]{0,8}(\\.\\d{2}$)?";
    private static final String CURRENCY_REGEX = "(BYN|USD|EUR)";
    private static final String TEXT_REGEX = "[}{><]";
    private static final String WORK_EXPERIENCE_REGEX = "(WITHOUT|UP_TO_A_YEAR|FROM_1_TO_3_YEARS|FROM_3_TO_5_YEARS|MORE_THAN_5_YEARS)";


    private static VacancyValidator instance;

    private VacancyValidator() {
    }

    public static VacancyValidator getInstance() {
        if (instance == null) {
            instance = new VacancyValidator();
        }
        return instance;
    }

    public boolean validateJobTitle(String vacancy) {
        return validate(vacancy, JOB_TITLE_REGEX);
    }

    public boolean validateSalary(String salary) {
        return validate(salary, SALARY_REGEX);
    }

    public boolean validateCurrency(String currency) {
        return validate(currency, CURRENCY_REGEX);
    }

    public boolean validateWorkExperience(String currency) {
        return validate(currency, WORK_EXPERIENCE_REGEX);
    }

    public boolean validateText(String text) {
        Pattern pattern = Pattern.compile(TEXT_REGEX);
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }

    public boolean isValidVacancyData(RequestContent requestContent) {
        HashMap<String, String[]> registrationUserDataMap = requestContent.getRequestParameters();
        String jobTitle = getParameter(registrationUserDataMap, ParameterName.VACANCY_JOB_TITLE);
        String salary = getParameter(registrationUserDataMap, ParameterName.VACANCY_SALARY);
        String currency = getParameter(registrationUserDataMap, ParameterName.VACANCY_CURRENCY);
        String workExperience = getParameter(registrationUserDataMap, ParameterName.VACANCY_WORK_EXPERIENCE);
        String responsibility = getParameter(registrationUserDataMap, ParameterName.VACANCY_RESPONSIBILITIES);
        String requirement = getParameter(registrationUserDataMap, ParameterName.VACANCY_REQUIREMENTS);
        String workingConditions = getParameter(registrationUserDataMap, ParameterName.VACANCY_WORKING_CONDITIONS);
        boolean result = true;
        if (!validateJobTitle(jobTitle)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN, true);
        }
        if (!validateSalary(salary)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN, true);
        }
        if (!validateCurrency(currency)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN, true);
        }
        if (!validateWorkExperience(workExperience)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN, true);
        }
        if (!validateText(responsibility)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_TEXT, true);
        }
        if (!validateText(requirement)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_TEXT, true);
        }
        if (!validateText(workingConditions)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_TEXT, true);
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
