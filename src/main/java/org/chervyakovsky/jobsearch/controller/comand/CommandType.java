package org.chervyakovsky.jobsearch.controller.comand;

import org.chervyakovsky.jobsearch.controller.comand.impl.DefaultCommand;
import org.chervyakovsky.jobsearch.controller.comand.impl.LoginCommand;
import org.chervyakovsky.jobsearch.controller.comand.impl.LogoutCommand;

import java.util.Arrays;

public enum CommandType {
    ADD_USER(new LoginCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

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
