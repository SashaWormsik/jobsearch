package org.chervyakovsky.jobsearch.controller.filter.permission;

import org.chervyakovsky.jobsearch.controller.command.CommandType;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UserPermission {

    ADMIN(Stream.of(
            CommandType.CHANGE_USER_STATUS,
            CommandType.CREATE_NEW_ADMIN,
            CommandType.GET_ALL_USERS,
            CommandType.GET_USER_INFO,
            CommandType.LOGOUT,
            CommandType.SEARCH_USER,
            CommandType.SEARCH_VACANCY,
            CommandType.DEFAULT,
            CommandType.INITIAL
    ).collect(Collectors.toSet())),

    COMPANY(Stream.of(
            CommandType.CHANGE_INTERVIEW_STATUS,
            CommandType.CHANGE_VACANCY_STATUS,
            CommandType.CREATE_VACANCY,
            CommandType.GET_ALL_INTERVIEWS,
            CommandType.GET_ALL_VACANCIES_FOR_COMPANY,
            CommandType.GET_INTERVIEW_INFO,
            CommandType.GET_USER_INFO,
            CommandType.GET_VACANCY_INFO,
            CommandType.GO_TO_UPDATE_THE_VACANCY,
            CommandType.LOGOUT,
            CommandType.SEARCH_VACANCY,
            CommandType.UPDATE_INTERVIEW,
            CommandType.UPDATE_USER_INFO,
            CommandType.UPDATE_USER_LOCATION,
            CommandType.UPDATE_VACANCY,
            CommandType.DEFAULT,
            CommandType.INITIAL
    ).collect(Collectors.toSet())),

    WORKER(Stream.of(
            CommandType.GET_ALL_INTERVIEWS,
            CommandType.GET_INTERVIEW_INFO,
            CommandType.GET_USER_INFO,
            CommandType.GET_VACANCY_INFO,
            CommandType.LOGOUT,
            CommandType.RESPOND,
            CommandType.SEARCH_VACANCY,
            CommandType.UPDATE_USER_INFO,
            CommandType.UPDATE_USER_LOCATION,
            CommandType.DEFAULT,
            CommandType.INITIAL
    ).collect(Collectors.toSet())),

    GUEST(Stream.of(
            CommandType.ACTIVATE_USER,
            CommandType.FORGOT_PASSWORD,
            CommandType.GET_USER_INFO,
            CommandType.GET_VACANCY_INFO,
            CommandType.LOGIN,
            CommandType.REGISTRATION,
            CommandType.RESET_PASSWORD,
            CommandType.SEARCH_VACANCY,
            CommandType.UPDATE_PASSWORD,
            CommandType.DEFAULT,
            CommandType.INITIAL
    ).collect(Collectors.toSet()));

    private final Set<CommandType> commands;

    UserPermission(Set<CommandType> commands) {
        this.commands = commands;
    }

    public static boolean isPermitted(UserRoleStatus roleStatus, CommandType commandType) {
        return Arrays.stream(UserPermission.values()).
                filter(userPermission -> userPermission.name().equals(roleStatus.name())).
                anyMatch(userPermission -> userPermission.commands.contains(commandType));
    }
}
