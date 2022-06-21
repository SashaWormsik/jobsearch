package org.chervyakovsky.jobsearch.controller.command;

import org.chervyakovsky.jobsearch.controller.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    ACTIVATE_USER(new ActivateUserCommand()),
    ADD_NEW_ADMIN(new AddNewAdminCommand()),
    CREATE_VACANCY(new CreateNewVacancyCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    GET_ALL_USERS(new GetAllUsersCommand()),
    GET_USER_INFO(new GetUserInfoCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    UPDATE_USER_INFO(new UpdateUserInfoCommand()),
    UPDATE_USER_LOCATION(new UpdateUserLocationCommand()),
    SEARCH_VACANCY(new SearchVacancyCommand()),
    SEARCH_USER(new SearchUserCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    REGISTRATION(new RegistrationCommand()),

    DEFAULT(new DefaultCommand()), // TODO
    INITIAL(new InitialCommand()); // TODO

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = DEFAULT;
        if (commandStr != null) {
            current = Arrays.stream(CommandType.values()).
                    filter(commandType -> commandType.name().equals(commandStr.toUpperCase())).
                    findFirst().
                    orElse(DEFAULT);
        }
        return current.command;
    }
}
