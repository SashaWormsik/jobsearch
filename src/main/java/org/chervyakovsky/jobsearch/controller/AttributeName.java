package org.chervyakovsky.jobsearch.controller;

public final class AttributeName {

    // if correct login page
    public static final String USER = "user";
    public static final String USER_LOCATION = "user_location";
    public static final String VACANCY = "vacancy";
    public static final String VACANCY_LOCATION = "vacancy_location";
    public static final String TEMP_USER = "temp_user";
    public static final String LIST_USERS = "list_users";

    // if incorrect login page

    public static final String ACCOUNT_IS_BLOCKED = "account_is_blocked";

    // after activate user
    public static final String ACTIVATE_USER = "activate_user";

    // forgot password
    public static final String FORGOT_PASSWORD = "forgot_password";


    // message
    public static final String INCORRECT_LOCATION_COUNTRY = "incorrect_location_country";
    public static final String INCORRECT_LOCATION_CITY = "incorrect_location_city";
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "incorrect_login_or_password";
    public static final String INCORRECT_ROLE = "incorrect_role";
    public static final String INCORRECT_LOGIN = "incorrect_login";
    public static final String INCORRECT_EMAIL = "incorrect_email";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    public static final String INCORRECT_CONFIRM_PASSWORD = "incorrect_confirm_password";
    public static final String INCORRECT_USER_NAME = "incorrect_user_name";
    public static final String INCORRECT_USER_SURNAME = "incorrect_user_surname";
    public static final String INCORRECT_EDUCATION = "incorrect_education";
    public static final String INCORRECT_WORKING_STATUS = "incorrect_working_status";
    public static final String INCORRECT_PROFESSION = "incorrect_profession";
    public static final String INCORRECT_DESCRIPTION = "incorrect_description";
    public static final String EXIST_LOGIN_OR_EMAIL = "exist_login_or_email";
    public static final String SUCCESSFUL_REGISTRATION = "temp_successful_registration";


    private AttributeName() {
    }
}
