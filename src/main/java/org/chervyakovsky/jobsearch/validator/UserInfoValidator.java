package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.regex.Pattern;

public class UserInfoValidator {

    private static final String LOGIN_REGEX = "^[\\w&&\\D]\\w{4,20}$"; // начало любые символы плюс минимум 20 символов (буквы цифры)
    private static final String EMAIL_REGEX = "^[\\p{Alpha}\\p{Digit}_!#$%&'*+/=?`{|}~^.-]+@[\\p{Alpha}\\p{Digit}.-]+$";
    private static final String ROLE_REGEX = "(ADMIN|COMPANY|WORKER)";
    private static final String NAME_SURNAME_REGEX = "^[\\p{L}`-]{1,20}$";
    private static final String WORKING_STATUS_REGEX = "(WORK|IN_SEARCH|NO_STATUS)";
    private static final String EDUCATION_STATUS_REGEX = "(HIGHER|SECONDARY|BASIC|NO_EDUCATION|NOT_SPECIFIED)";
    private static final String PROFESSION_REGEX = "^[\\p{L}]+[-`]?[\\p{L}]+$"; // минимум две буквы, может быть - или ` между словами
    private static final String DESCRIPTION_REGEX = "";
    private static final String PASSWORD_REGEX = "^(?=.*[\\p{Alpha}])(?=.*\\d)[\\p{Alpha}\\d]{8,20}$"; //Минимум восемь символов, минимум одна буква и одна цифра

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
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        if (login == null || login.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(login).matches();
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public boolean validateRole(String role){
        Pattern pattern = Pattern.compile(ROLE_REGEX);
        if(role == null || role.trim().isEmpty()){
            return false;
        }
        return pattern.matcher(role).matches();
    }

    public boolean validateName(String name) {
        Pattern pattern = Pattern.compile(NAME_SURNAME_REGEX);
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(name).matches();
    }

    public boolean validateWorkingStatus(String workingStatus){
        Pattern pattern = Pattern.compile(WORKING_STATUS_REGEX);
        if(workingStatus == null || workingStatus.trim().isEmpty()){
            return false;
        }
        return pattern.matcher(workingStatus).matches();
    }

    public boolean validateEducation(String education){
        Pattern pattern = Pattern.compile(EDUCATION_STATUS_REGEX);
        if(education == null || education.trim().isEmpty()){
            return false;
        }
        return pattern.matcher(education).matches();
    }

    public  boolean validateProfession(String profession){
        Pattern pattern = Pattern.compile(PROFESSION_REGEX);
        if (profession == null || profession.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(profession).matches();
    }

    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    public boolean isValidUserData(RequestContent requestContent) {
        return false;
    }
}
