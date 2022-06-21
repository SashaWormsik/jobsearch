package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import static org.chervyakovsky.jobsearch.controller.AttributeName.EXIST_LOGIN_OR_EMAIL;
import static org.chervyakovsky.jobsearch.controller.AttributeName.SUCCESSFUL_REGISTRATION;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (!userService.existLoginOrEmail(requestContent)) {
                if (userService.registrationNewUser(requestContent)) {
                    requestContent.setNewValueInSessionAttribute(SUCCESSFUL_REGISTRATION, true);
                    router.setPage(PagePath.LOGIN_PAGE);
                    router.setType(Router.Type.REDIRECT);
                } else {
                    requestContent.setParameterInAttribute();
                    router.setPage(PagePath.REGISTRATION_PAGE);
                    router.setType(Router.Type.FORWARD);
                }
            } else {
                requestContent.setParameterInAttribute();
                requestContent.setNewValueInRequestAttributes(EXIST_LOGIN_OR_EMAIL, true);
                router.setPage(PagePath.REGISTRATION_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
