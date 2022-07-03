package org.chervyakovsky.jobsearch.controller.command;

import org.chervyakovsky.jobsearch.controller.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    ACTIVATE_USER(new ActivateUserCommand()),
    ADD_NEW_ADMIN(new CreateNewAdminCommand()),
    CREATE_VACANCY(new CreateNewVacancyCommand()),
    CHANGE_INTERVIEW_STATUS(new ChangeInterviewStatusCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CHANGE_VACANCY_STATUS(new ChangeVacancyStatusCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    GET_ALL_INTERVIEWS(new GetAllInterviewForUserCommand()),
    GET_ALL_USERS(new GetAllUsersCommand()),
    GET_ALL_VACANCIES_FOR_COMPANY(new GetAllVacanciesForCompanyCommand()),
    GET_INTERVIEW_INFO(new GetInterviewInfoCommand()),
    GET_USER_INFO(new GetUserInfoCommand()),
    GET_VACANCY_INFO(new GetVacancyInfoCommand()),
    GO_TO_UPDATE_THE_VACANCY(new GoToUpdateTheVacancyCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    RESET_PASSWORD(new ResetPasswordCommand()),
    RESPOND(new RespondCommand()),
    SEARCH_USER(new SearchUserCommand()),
    SEARCH_VACANCY(new SearchVacancyCommand()),
    UPDATE_INTERVIEW(new UpdateInterviewCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    UPDATE_USER_INFO(new UpdateUserInfoCommand()),
    UPDATE_USER_LOCATION(new UpdateUserLocationCommand()),
    UPDATE_VACANCY(new UpdateVacancyCommand()),

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
