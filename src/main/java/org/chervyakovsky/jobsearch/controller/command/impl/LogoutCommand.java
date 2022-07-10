package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

/**
 * The {@link Command} that is used to sign out user by invalidation of its associated session.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class LogoutCommand implements Command {

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#LOGIN_PAGE}.
     */
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
