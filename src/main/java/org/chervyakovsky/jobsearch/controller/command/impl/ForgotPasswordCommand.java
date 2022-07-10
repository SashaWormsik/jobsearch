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
 * The {@link Command}  that  sends messages to the email address if he forgot the password.
 * The user must enter their email address on the page when registering.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class ForgotPasswordCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#FORWARD} to {@link PagePath#FORGOT_PASSWORD_PAGE}.
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.sendEmailToRecoverPassword(requestContent)) {
                requestContent.setNewValueInRequestAttributes(AttributeName.FORGOT_PASSWORD, true);
            } else {
                requestContent.setParameterInAttribute();
                requestContent.setNewValueInRequestAttributes(AttributeName.FORGOT_PASSWORD, false);
            }
            router.setType(Router.Type.FORWARD);
            router.setPage(PagePath.FORGOT_PASSWORD_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
