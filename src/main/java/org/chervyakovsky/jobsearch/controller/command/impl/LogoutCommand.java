package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new Router(PagePath.LOGIN_PAGE, Router.Type.REDIRECT);
    }
}
