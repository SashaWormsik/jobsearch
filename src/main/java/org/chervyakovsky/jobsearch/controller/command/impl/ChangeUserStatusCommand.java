package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import static org.chervyakovsky.jobsearch.controller.AttributeName.TEMP_USER;

/**
 * The {@link Command} that changes the user's status.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class ChangeUserStatusCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#USER_INFO_PAGE} if successful,
     * otherwise with type {@link Router.Type#FORWARD} to {@link PagePath#USER_INFO_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.changeUserStatus(requestContent)) {
                UserInfo userInfo = userService.findUserById(requestContent).orElse(new UserInfo());
                requestContent.setNewValueInSessionAttribute(TEMP_USER, userInfo);
                router.setType(Router.Type.REDIRECT);
            } else {
                UserInfo userInfo = userService.findUserById(requestContent).orElse(new UserInfo());
                requestContent.setNewValueInRequestAttributes(TEMP_USER, userInfo);
                router.setType(Router.Type.FORWARD);
            }
            router.setPage(PagePath.USER_INFO_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
