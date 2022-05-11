package org.chervyakovsky.jobsearch.validator;

import java.util.regex.Pattern;

public class UserInfoValidator {

    private static final String LOGIN_REGEX = "^[\\w&&\\D]\\w{4,20}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$"; //Минимум восемь символов, минимум одна буква и одна цифра

    private static UserInfoValidator instance;

    private UserInfoValidator(){
    }


    public static UserInfoValidator getInstance(){
        if(instance == null){
            instance = new UserInfoValidator();
        }
        return instance;
    }

    public boolean validateLogin(String login){
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        if(login == null || login.trim().isEmpty()){
            return false;
        }
        return pattern.matcher(login).matches();
    }

    public boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        if(password == null || password.trim().isEmpty()){
            return false;
        }
        return pattern.matcher(password).matches();
    }
}
