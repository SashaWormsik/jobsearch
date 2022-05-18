package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;

public class InitialCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Router router = new Router();
        router.setType(Router.Type.FORWARD);
        router.setPage(PagePath.MAIN_PAGE);
        request.setAttribute("MESSAGE", "ГЛАВНАЯ СТРАНИЦА СЮДА ВАКАНСИИ ДЛЯ ГОСТЯ И ВСЕХ");
        return router;
    }
}
