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

/**
 * The {@link Command}  that creates a new administrator.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class CreateNewAdminCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#ADMIN_ADD_NEW_ADMIN_PAGE} if successful,
     * otherwise with type {@link Router.Type#FORWARD} to {@link PagePath#ADMIN_ADD_NEW_ADMIN_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (!userService.existLoginOrEmail(requestContent)) {
                if (userService.registrationNewUser(requestContent)) {
                    requestContent.setNewValueInSessionAttribute(SUCCESSFUL_REGISTRATION, true);
                    router.setType(Router.Type.REDIRECT);
                } else {
                    requestContent.setParameterInAttribute();
                    router.setType(Router.Type.FORWARD);
                }
            } else {
                requestContent.setParameterInAttribute();
                requestContent.setNewValueInRequestAttributes(EXIST_LOGIN_OR_EMAIL, true);
                router.setType(Router.Type.FORWARD);
            }
            router.setPage(PagePath.ADMIN_ADD_NEW_ADMIN_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
