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
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.InterviewService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.VacancyService;
import org.chervyakovsky.jobsearch.model.service.impl.InterviewServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.VacancyServiceImpl;

import java.util.Map;
import java.util.Optional;

public class ChangeInterviewStatusCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        router.setPage(PagePath.INTERVIEW_INFO_PAGE);
        InterviewService interviewService = InterviewServiceImpl.getInstance();
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (interviewService.changeInterviewStatus(requestContent)){
                router.setType(Router.Type.REDIRECT);
            }
            Optional<Interview> optionalInterview = interviewService.findInterviewById(requestContent);
            Optional<UserInfo> optionalUserInfo = userService.findUserById(requestContent);
            Map<Vacancy, Map.Entry<Location, UserInfo>> vacancy = vacancyService.findVacancyById(requestContent);
            optionalUserInfo.ifPresent(s -> requestContent.setNewValueInSessionAttribute(AttributeName.TEMP_USER, s));
            optionalInterview.ifPresent(s -> requestContent.setNewValueInSessionAttribute(AttributeName.TEMP_INTERVIEW, s));
            requestContent.setNewValueInSessionAttribute(AttributeName.TEMP_VACANCY, vacancy.entrySet().iterator().next());
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
