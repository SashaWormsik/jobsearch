package org.chervyakovsky.jobsearch.controller.command;

import org.chervyakovsky.jobsearch.controller.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    DEFAULT(new DefaultCommand()), // TODO
    INITIAL(new InitialCommand()), // TODO
    ACTIVATE_USER(new ActivateUserCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    SEARCH_VACANCY(new SearchVacancyCommand()),
    CREATE_VACANCY(new CreateNewVacancy());

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
