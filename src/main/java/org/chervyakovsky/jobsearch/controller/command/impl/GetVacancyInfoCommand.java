package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.model.service.impl.VacancyServiceImpl;

import java.util.Map;

public class GetVacancyInfoCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.VACANCY_INFO_PAGE);
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            Map<Vacancy, Map.Entry<Location, UserInfo>> vacancy = vacancyService.findVacancyById(requestContent);
            Map.Entry<Vacancy, Map.Entry<Location, UserInfo>> entryVacancy = vacancy.entrySet().iterator().next();
            requestContent.setNewValueInRequestAttributes(AttributeName.TEMP_VACANCY, entryVacancy);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}