package org.chervyakovsky.jobsearch.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.exception.CommandException;

@FunctionalInterface
public interface Command {

    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
