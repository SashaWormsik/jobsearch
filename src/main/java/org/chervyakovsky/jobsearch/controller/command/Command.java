package org.chervyakovsky.jobsearch.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

@FunctionalInterface
public interface Command {

    Router execute(RequestContent requestContent) throws CommandException;
}
