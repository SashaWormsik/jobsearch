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
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.CredentialService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.CredentialServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

/**
 * The {@link Command} that updates the password for the user.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class UpdatePasswordCommand implements Command {
    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#LOGIN_PAGE} if successful,
     * otherwise with type {@link Router.Type#FORWARD} to {@link PagePath#RESET_PASSWORD_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        CredentialService credentialService = CredentialServiceImpl.getInstance();
        Router router = new Router();
        try {
            Optional<UserInfo> optionalUserInfo = userService.findUserByToken(requestContent);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                userInfo.setUserToken(null);
                if (credentialService.updatePasswordByUserId(requestContent, userInfo.getId()) && userService.updateUser(userInfo)) {
                    requestContent.setNewValueInSessionAttribute(AttributeName.PASSWORD_CHANGED, true);
                    router.setType(Router.Type.REDIRECT);
                    router.setPage(PagePath.LOGIN_PAGE);
                } else {
                    requestContent.setNewValueInRequestAttributes(AttributeName.SOMETHING_WENT_WRONG, true);
                    router.setType(Router.Type.FORWARD);
                    router.setPage(PagePath.RESET_PASSWORD_PAGE);
                }
            } else {
                requestContent.setNewValueInRequestAttributes(AttributeName.USER_NOT_FOUND, true);
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.RESET_PASSWORD_PAGE);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
