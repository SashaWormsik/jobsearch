package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Router router = new Router();
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request); // FIXME 18.05.2022
        if(userService.registrationNewUser(requestContent)){
            requestContent.insertAttribute(request);
            router.setPage(PagePath.LOGIN_PAGE);
            router.setType(Router.Type.REDIRECT);
        }
        else {
            requestContent.insertAttribute(request);
            router.setPage(PagePath.REGISTRATION_PAGE);
            router.setType(Router.Type.FORWARD);
        }
        return router;
    }
}
