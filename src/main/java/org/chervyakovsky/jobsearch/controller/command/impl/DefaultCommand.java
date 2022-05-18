package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null; // fixme
    }
}
