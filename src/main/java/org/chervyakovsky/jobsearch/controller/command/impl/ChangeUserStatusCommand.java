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

public class ChangeUserStatusCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

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
            router.setPage(PagePath.ADMIN_USER_INFO_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
