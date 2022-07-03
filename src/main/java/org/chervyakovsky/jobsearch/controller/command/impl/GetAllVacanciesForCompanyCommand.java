package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.model.service.impl.VacancyServiceImpl;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.util.Map;

public class GetAllVacanciesForCompanyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.COMPANY_ALL_VACANCIES_PAGE);
        router.setType(Router.Type.FORWARD);
        Pageable pageable = new Pageable();
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            Map<Vacancy, Location> result = vacancyService.searchVacanciesForCompany(requestContent, pageable);
            requestContent.setNewValueInRequestAttributes(AttributeName.VACANCIES, result);
            requestContent.setNewValueInRequestAttributes(ParameterName.PAGE, pageable.getPage());
            requestContent.setNewValueInRequestAttributes(ParameterName.PAGE_COUNT, pageable.getPageCount());
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}