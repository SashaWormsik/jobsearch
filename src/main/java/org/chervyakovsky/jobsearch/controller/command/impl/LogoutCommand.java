package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) {
        HttpSession session = requestContent.getSession();
        if (session != null) {
            session.invalidate();
            requestContent.getSessionAttribute().clear();
            requestContent.getRequestParameters().clear();
            requestContent.getRequestAttribute().clear();
        }
        return new Router(PagePath.LOGIN_PAGE, Router.Type.REDIRECT);
    }
}
