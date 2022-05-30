package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

public class DefaultCommand implements Command {
    @Override
    public Router execute(RequestContent requestContent) {
        return null; // todo
    }
}
