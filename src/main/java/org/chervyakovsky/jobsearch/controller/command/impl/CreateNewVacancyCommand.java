package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
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

import static org.chervyakovsky.jobsearch.controller.AttributeName.SUCCESSFUL_REGISTRATION;

/**
 * The {@link Command}  that creates a new vacancy.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class CreateNewVacancyCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#COMPANY_ALL_VACANCIES_PAGE} if successful,
     * otherwise with type {@link Router.Type#FORWARD} to {@link PagePath#COMPANY_CREATE_VACANCY_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            if (vacancyService.createNewVacancy(requestContent)) {
                requestContent.setNewValueInSessionAttribute(SUCCESSFUL_REGISTRATION, true);
                router.setType(Router.Type.REDIRECT);
            } else {
                requestContent.setParameterInAttribute();
                router.setType(Router.Type.FORWARD);
            }
            router.setPage(PagePath.COMPANY_CREATE_VACANCY_PAGE);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
