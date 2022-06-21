package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.model.service.impl.VacancyServiceImpl;

public class CreateNewVacancyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            if (vacancyService.createNewVacancy(requestContent)) {
                router.setType(Router.Type.REDIRECT);
                router.setPage(PagePath.MAIN_PAGE);
            } else {
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.LOGIN_PAGE);
            }
        } catch (ServiceException exception) {
            exception.printStackTrace();
        }
        return router;
    }
}
