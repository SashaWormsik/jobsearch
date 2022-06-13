package org.chervyakovsky.jobsearch.controller.command.impl;

import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

public class MakeVacancyIrrelevant implements Command {
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        return null;
    }
}
