package org.chervyakovsky.jobsearch.model.mapper;

public class ColumnName {

    // USER_INFO
    public static final String USER_ID = "u_user_info_id";
    public static final String USER_LOGIN = "u_login";
    public static final String USER_MAIL = "u_email";
    public static final String USER_ROLE = "u_role";
    public static final String USER_STATUS = "u_user_status";
    public static final String USER_LOCATION = "u_location_id";
    public static final String USER_NAME = "u_user_name";
    public static final String USER_SURNAME = "u_user_surname";
    public static final String USER_WORKING_STATUS = "u_working_status";
    public static final String USER_EDUCATION = "u_education";
    public static final String USER_PROFESSION = "u_profession";
    public static final String USER_DESCRIPTION = "u_description";
    public static final String USER_TOKEN = "u_user_token";
	public static final String USER_IMAGE = "u_image";

	// CREDENTIAL
    public static final String CREDENTIAL_ID = "c_credential_id";
    public static final String CREDENTIAL_PASSWORD = "c_password";
    public static final String CREDENTIAL_ACTIVE = "c_active";
    public static final String CREDENTIAL_CREATE_DATE = "c_create_date";
    public static final String CREDENTIAL_USER_INFO_ID = "c_user_info_id";

    // LOCATION
    public static final String LOCATION_ID = "l_location_id";
    public static final String LOCATION_COUNTRY = "l_country";
    public static final String LOCATION_CITY = "l_city";

    // VACANCY
    public static final String VACANCY_ID = "v_vacancy_id";
    public static final String VACANCY_CREATE_DATE = "v_create_date";
    public static final String VACANCY_JOB_TITLE = "v_job_title";
    public static final String VACANCY_COMPANY_ID = "v_company_id";
    public static final String VACANCY_LOCATION_ID = "v_location_id";
    public static final String VACANCY_SALARY = "v_salary";
    public static final String VACANCY_CURRENCY = "v_currency";
    public static final String VACANCY_WORK_EXPERIENCE = "v_work_experience";
    public static final String VACANCY_RESPONSIBILITIES = "v_responsibilities";
    public static final String VACANCY_REQUIREMENT = "v_requirement";
    public static final String VACANCY_WORKING_CONDITION = "v_working_conditions";
    public static final String VACANCY_STATUS = "v_vacancy_status";
    public static final String COUNT_ROWS = "total_count";


    private ColumnName() {
    }
}
