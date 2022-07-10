package org.chervyakovsky.jobsearch.controller.command.impl;

import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

/**
 * The {@link Command}  that is executed when an unknown command is received.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class DefaultCommand implements Command {

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#FORWARD} to {@link PagePath#MAIN_PAGE}.
     */
    @Override
    public Router execute(RequestContent requestContent) {
        Router router = new Router();
        router.setPage(PagePath.MAIN_PAGE);
        router.setType(Router.Type.FORWARD);
        return router;
    }
}
