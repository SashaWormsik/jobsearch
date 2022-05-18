package org.chervyakovsky.jobsearch.controller.command;

import org.chervyakovsky.jobsearch.controller.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    DEFAULT(new DefaultCommand()),
    INITIAL(new InitialCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = DEFAULT;
        String commandName = commandStr.toUpperCase();
        if(checkingExistenceCommand(commandName)){
            current = CommandType.valueOf(commandName);
        }
        return current.command;
    }

    private static boolean checkingExistenceCommand(String commandName) {
        return Arrays.stream(CommandType.values()).
                anyMatch(commandType -> commandType.name().equals(commandName));
    }
}
