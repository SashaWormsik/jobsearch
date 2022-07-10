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
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.InterviewService;
import org.chervyakovsky.jobsearch.model.service.impl.InterviewServiceImpl;

import java.util.List;

/**
 * The {@link Command} that finds all the interviews for the company.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class GetAllInterviewForUserCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#FORWARD} to {@link PagePath#ALL_INTERVIEW_PAGE}.
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.ALL_INTERVIEW_PAGE);
        InterviewService interviewService = InterviewServiceImpl.getInstance();
        try {
            List<Interview> interviewList = interviewService.findAllUserInterview(requestContent);
            requestContent.setNewValueInRequestAttributes(AttributeName.LIST_INTERVIEWS, interviewList);
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
