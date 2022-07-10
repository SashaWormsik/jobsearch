package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyValidator {

    private static final String JOB_TITLE_REGEX = "^([a-zA-ZА-Яа-я]{1})([\\ha-zа-я-]{1,20})([a-zа-я]{1})$";
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
        if (text == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(TEXT_REGEX);
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }

    public boolean isValidVacancyData(RequestContent requestContent) {
        String jobTitle = requestContent.getParameterFromRequest(ParameterName.VACANCY_JOB_TITLE);
        String salary = requestContent.getParameterFromRequest(ParameterName.VACANCY_SALARY);
        String currency = requestContent.getParameterFromRequest(ParameterName.VACANCY_CURRENCY);
        String workExperience = requestContent.getParameterFromRequest(ParameterName.VACANCY_WORK_EXPERIENCE);
        String responsibility = requestContent.getParameterFromRequest(ParameterName.VACANCY_RESPONSIBILITIES);
        String requirement = requestContent.getParameterFromRequest(ParameterName.VACANCY_REQUIREMENTS);
        String workingConditions = requestContent.getParameterFromRequest(ParameterName.VACANCY_WORKING_CONDITIONS);
        boolean result = true;
        if (!validateJobTitle(jobTitle)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_PROFESSION, true);
        }
        if (!validateSalary(salary)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_SALARY, true);
        }
        if (!validateCurrency(currency)) {
            result = false;
        }
        if (!validateWorkExperience(workExperience)) {
            result = false;
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
}
