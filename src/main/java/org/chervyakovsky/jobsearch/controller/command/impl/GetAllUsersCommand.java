package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.List;

public class GetAllUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.ALL_USERS_PAGE);
        router.setType(Router.Type.FORWARD);
        Pageable pageable = new Pageable();
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<UserInfo> userInfoList = userService.findAllUser(requestContent, pageable);
            requestContent.setNewValueInRequestAttributes(AttributeName.LIST_USERS, userInfoList);
            requestContent.setNewValueInRequestAttributes(ParameterName.PAGE, pageable.getPage());
            requestContent.setNewValueInRequestAttributes(ParameterName.PAGE_COUNT, pageable.getPageCount());
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new CommandException(exception); // TODO
        }
        return router;
    }

}
