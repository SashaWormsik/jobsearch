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

/**
 * The {@link Command}  that gives information about the vacancy and directs to the editing page.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class GoToUpdateTheVacancyCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#FORWARD} to {@link PagePath#COMPANY_UPDATE_VACANCY_INFO_PAGE}.
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.COMPANY_UPDATE_VACANCY_INFO_PAGE);
        router.setType(Router.Type.FORWARD);
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            Map<Vacancy, Map.Entry<Location, UserInfo>> vacancy = vacancyService.findVacancyById(requestContent);
            Map.Entry<Vacancy, Map.Entry<Location, UserInfo>> entryVacancy = vacancy.entrySet().iterator().next();
            requestContent.setNewValueInSessionAttribute(AttributeName.TEMP_VACANCY, entryVacancy);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
