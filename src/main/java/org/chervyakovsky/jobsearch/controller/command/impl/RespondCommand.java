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
import org.chervyakovsky.jobsearch.model.service.InterviewService;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.model.service.impl.InterviewServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.VacancyServiceImpl;

import java.util.Map;

/**
 * The {@link Command} which is designed to respond to a vacancy.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class RespondCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to {@link PagePath#VACANCY_INFO_PAGE} if successful,
     * otherwise with type {@link Router.Type#FORWARD} to {@link PagePath#VACANCY_INFO_PAGE}
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.VACANCY_INFO_PAGE);
        InterviewService interviewService = InterviewServiceImpl.getInstance();
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            Map<Vacancy, Map.Entry<Location, UserInfo>> vacancy = vacancyService.findVacancyById(requestContent);
            Map.Entry<Vacancy, Map.Entry<Location, UserInfo>> entryVacancy = vacancy.entrySet().iterator().next();
            if (interviewService.createNewInterview(requestContent)) {
                requestContent.setNewValueInSessionAttribute(AttributeName.RESPONSE_TO_A_VACANCY, true);
                requestContent.setNewValueInSessionAttribute(AttributeName.TEMP_VACANCY, entryVacancy);
                router.setType(Router.Type.REDIRECT);
            } else {
                requestContent.setNewValueInRequestAttributes(AttributeName.RESPONSE_TO_A_VACANCY, false);
                requestContent.setNewValueInRequestAttributes(AttributeName.TEMP_VACANCY, entryVacancy);
                router.setType(Router.Type.FORWARD);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
