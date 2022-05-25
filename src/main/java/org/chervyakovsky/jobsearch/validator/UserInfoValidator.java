package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.controller.PropertyKey;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.regex.Pattern;

public class UserInfoValidator {

    private static final String LOGIN_REGEX = "^[@\\p{Alpha}]\\w{4,20}$";
    private static final String EMAIL_REGEX = "^[\\p{Alpha}\\p{Digit}_!#$%&'*+/=?`{|}~^.-]+@[\\p{Alpha}\\p{Digit}.-]+$";
    private static final String ROLE_REGEX = "(ADMIN|COMPANY|WORKER)";
    private static final String NAME_SURNAME_REGEX = "^[\\p{L}][\\p{L}`-]?[\\p{L}`]{1,20}$";
    private static final String WORKING_STATUS_REGEX = "(WORK|IN_SEARCH|NO_STATUS)";
    private static final String EDUCATION_STATUS_REGEX = "(HIGHER|SECONDARY|BASIC|NO_EDUCATION|NOT_SPECIFIED)";
    private static final String PROFESSION_REGEX = "^[\\p{L}]+[-`]?[\\p{L}]+$"; // TODO
    private static final String DESCRIPTION_REGEX = ""; // TODO
    private static final String PASSWORD_REGEX = "^(?=.*[\\p{Alpha}])(?=.*\\d)[\\p{Alpha}\\d]{8,20}$";

    private static UserInfoValidator instance;

    private UserInfoValidator() {
    }

    public static UserInfoValidator getInstance() {
        if (instance == null) {
            instance = new UserInfoValidator();
        }
        return instance;
    }

    public boolean validateLogin(String login) {
        return validate(login, LOGIN_REGEX);
    }

    public boolean validateEmail(String email) {
        return validate(email, EMAIL_REGEX);
    }

    public boolean validateRole(String role) {
        return validate(role, ROLE_REGEX);
    }

    public boolean validateName(String name) {
        return validate(name, NAME_SURNAME_REGEX);
    }

    public boolean validateWorkingStatus(String workingStatus) {
        return validate(workingStatus, WORKING_STATUS_REGEX);
    }

    public boolean validateEducation(String education) {
        return validate(education, EDUCATION_STATUS_REGEX);
    }

    public boolean validateProfession(String profession) {
        return validate(profession, PROFESSION_REGEX);
    }

    public boolean validatePassword(String password) {
        return validate(password, PASSWORD_REGEX);
    }

    public boolean isValidLoginUserData(RequestContent requestContent){
        HashMap<String, String[]> loginUserDataMap = requestContent.getRequestParameters();
        String login = getParameter(loginUserDataMap, ParameterName.USER_LOGIN);
        String password = getParameter(loginUserDataMap, ParameterName.CREDENTIAL_PASSWORD);
        return validateLogin(login) && validatePassword(password);
    }

    public boolean isValidRegistrationUserData(RequestContent requestContent) {
        boolean result = true;
        HashMap<String, String[]> registrationUserDataMap = requestContent.getRequestParameters();
        String role = getParameter(registrationUserDataMap, ParameterName.USER_ROLE);
        if (!validateRole(role)) {
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_ROLE, true);
            return false;
        } else {
            UserRoleStatus userRoleStatus = UserRoleStatus.valueOf(role);
            switch (userRoleStatus) {
                case COMPANY:
                    result = validateCompanyData(requestContent);
                    break;
                case WORKER:
                    result = validateWorkerData(requestContent);
                    break;
                case ADMIN:
                    result = validateAdminData(requestContent);
                    break;
            }
        }
        return result;
    }

    private boolean validateCompanyData(RequestContent requestContent) {
        return basicUserDataValidation(requestContent);
    }

    private boolean validateAdminData(RequestContent requestContent) {
        boolean result = basicUserDataValidation(requestContent);
        HashMap<String, String[]> registrationUserDataMap = requestContent.getRequestParameters();
        String userSurname = getParameter(registrationUserDataMap, ParameterName.USER_SURNAME);
        if (!validateName(userSurname)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_USER_SURNAME, true);
        }
        return result;
    }

    private boolean validateWorkerData(RequestContent requestContent) {
        boolean result = basicUserDataValidation(requestContent);
        HashMap<String, String[]> registrationUserDataMap = requestContent.getRequestParameters();
        String userSurname = getParameter(registrationUserDataMap, ParameterName.USER_SURNAME);
        String education = getParameter(registrationUserDataMap, ParameterName.USER_EDUCATION_STATUS);
        String workingStatus = getParameter(registrationUserDataMap, ParameterName.USER_WORKING_STATUS);
        if (!validateName(userSurname)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_USER_SURNAME, true);
        }
        if (!validateEducation(education)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_EDUCATION, true);
        }
        if (!validateWorkingStatus(workingStatus)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_WORKING_STATUS, true);
        }
        return result;
    }

    private boolean basicUserDataValidation(RequestContent requestContent) {
        HashMap<String, String[]> registrationUserDataMap = requestContent.getRequestParameters();
        String login = getParameter(registrationUserDataMap, ParameterName.USER_LOGIN);
        String email = getParameter(registrationUserDataMap, ParameterName.USER_EMAIL);
        String password = getParameter(registrationUserDataMap, ParameterName.CREDENTIAL_PASSWORD);
        String confirmPassword = getParameter(registrationUserDataMap, ParameterName.CREDENTIAL_CONFIRM_PASSWORD);
        String userName = getParameter(registrationUserDataMap, ParameterName.USER_NAME);
        boolean result = true;
        if (!validateLogin(login)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN, true);
        }
        if (!validateEmail(email)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_EMAIL, true);
        }
        if (!validatePassword(password)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_PASSWORD, true);
        }
        if (password != null && !password.equals(confirmPassword)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_CONFIRM_PASSWORD, true);
        }
        if (!validateName(userName)) {
            result = false;
            requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_USER_NAME, true);
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
