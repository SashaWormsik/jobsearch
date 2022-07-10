package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

/**
 * The {@link Command} that activates the user's account after clicking on the link sent by mail.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class ActivateUserCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#FORWARD} to {@link PagePath#LOGIN_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.activateUserAccount(requestContent)) {
                requestContent.setNewValueInRequestAttributes(AttributeName.ACTIVATE_USER, true);
            } else {
                requestContent.setNewValueInRequestAttributes(AttributeName.ACTIVATE_USER, false);
            }
            router.setType(Router.Type.FORWARD);
            router.setPage(PagePath.LOGIN_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
