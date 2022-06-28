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

public class ActivateUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if(userService.activateUserAccount(requestContent)){
                requestContent.setNewValueInRequestAttributes(AttributeName.ACTIVATE_USER, true);
            }else {
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
