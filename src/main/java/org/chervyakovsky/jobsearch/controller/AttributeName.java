package org.chervyakovsky.jobsearch.controller;

public final class AttributeName {

    // if correct login page
    public static final String USER = "user";
    public static final String USER_LOCATION = "location";

    // if incorrect login page
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "incorrect_login_or_password";
    public static final String ACCOUNT_IS_BLOCKED = "account_is_blocked";

    // after successful registration
    public static final String SUCCESSFUL_REGISTRATION = "temp_successful_registration";

    // after activate user
    public static final String ACTIVATE_USER = "activate_user";

    // forgot password
    public static final String FORGOT_PASSWORD = "forgot_password";

    // incorrect registration page
    public static final String INCORRECT_ROLE = "incorrect_role";
    public static final String INCORRECT_LOGIN = "incorrect_login";
    public static final String INCORRECT_EMAIL = "incorrect_email";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    public static final String INCORRECT_CONFIRM_PASSWORD = "incorrect_confirm_password";
    public static final String INCORRECT_USER_NAME = "incorrect_user_name";
    public static final String INCORRECT_USER_SURNAME = "incorrect_user_surname";
    public static final String INCORRECT_EDUCATION = "incorrect_education";
    public static final String INCORRECT_WORKING_STATUS = "incorrect_working_status";
    public static final String INCORRECT_EXIST_LOGIN_OR_EMAIL = "exist_login_or_email";


    private AttributeName() {
    }
}
