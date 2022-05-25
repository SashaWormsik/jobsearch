package org.chervyakovsky.jobsearch.controller;

public final class AttributeName {

    // correct login page
    public static final String USER = "user";
    public static final String USER_LOCATION = "location";

    // incorrect login page
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "incorrect_login_or_password";

    // incorrect registration page
    public static final String INCORRECT_REGISTRATION_USER = "incorrect_registration_user";
    public static final String INCORRECT_ROLE = "incorrect_role";
    public static final String INCORRECT_LOGIN = "incorrect_login";
    public static final String INCORRECT_EMAIL = "incorrect_email";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    public static final String INCORRECT_CONFIRM_PASSWORD = "incorrect_confirm_password";
    public static final String INCORRECT_USER_NAME = "incorrect_user_name";
    public static final String INCORRECT_USER_SURNAME = "incorrect_user_surname";
    public static final String INCORRECT_EDUCATION = "incorrect_education";
    public static final String INCORRECT_WORKING_STATUS = "incorrect_working_status";

    // registration page if login or email exist
    public static final String EXIST_LOGIN_OR_EMAIL = "exist_login_or_email";


    private AttributeName() {
    }
}
