package org.chervyakovsky.jobsearch.controller.command;

import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

/**
 * The interface Command.
 */
@FunctionalInterface
public interface Command {
    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router
     * @throws CommandException the command exception
     */
    Router execute(RequestContent requestContent) throws CommandException;
}
