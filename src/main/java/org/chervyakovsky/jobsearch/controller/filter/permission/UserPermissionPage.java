package org.chervyakovsky.jobsearch.controller.filter.permission;

import org.chervyakovsky.jobsearch.controller.command.CommandType;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.chervyakovsky.jobsearch.controller.PagePath.*;


public enum UserPermissionPage {
    ADMIN(Stream.of(
            MAIN_PAGE,
            USER_INFO_PAGE,
            ACCOUNT_PAGE,
            ADMIN_ADD_NEW_ADMIN_PAGE,
            ADMIN_ALL_USERS_PAGE,
            ERROR_PAGE).collect(Collectors.toSet())),

    COMPANY(Stream.of(
            MAIN_PAGE,
            USER_INFO_PAGE,
            VACANCY_INFO_PAGE,
            ACCOUNT_PAGE,
            UPDATE_INFO_PAGE,
            ALL_INTERVIEW_PAGE,
            INTERVIEW_INFO_PAGE,
            COMPANY_ALL_VACANCIES_PAGE,
            COMPANY_CREATE_VACANCY_PAGE,
            COMPANY_UPDATE_VACANCY_INFO_PAGE,
            ERROR_PAGE).collect(Collectors.toSet())),

    WORKER(Stream.of(
            MAIN_PAGE,
            USER_INFO_PAGE,
            VACANCY_INFO_PAGE,
            ACCOUNT_PAGE,
            UPDATE_INFO_PAGE,
            ALL_INTERVIEW_PAGE,
            INTERVIEW_INFO_PAGE,
            ERROR_PAGE).collect(Collectors.toSet())),

    GUEST(Stream.of(
            MAIN_PAGE,
            LOGIN_PAGE,
            REGISTRATION_PAGE,
            FORGOT_PASSWORD_PAGE,
            RESET_PASSWORD_PAGE,
            USER_INFO_PAGE,
            VACANCY_INFO_PAGE,
            ERROR_PAGE).collect(Collectors.toSet()));

    private final Set<String> pages;

    UserPermissionPage(Set<String> pages) {
        this.pages = pages;
    }

    public static boolean isPermitted(UserRoleStatus roleStatus, String page) {
        return Arrays.stream(UserPermissionPage.values()).
                filter(userPermission -> userPermission.name().equals(roleStatus.name())).
                anyMatch(userPermission -> userPermission.pages.contains(page));
    }

}
