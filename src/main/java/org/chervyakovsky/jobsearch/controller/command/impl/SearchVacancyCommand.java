package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.ParameterName;
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

import java.util.HashMap;
import java.util.Map;

public class SearchVacancyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int RECORDS_PAGE = 10;
    private static final int START_PAGE = 1;
    private static final int DEFAULT_PAGE_COUNT = 0;


    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        Integer pageCount = DEFAULT_PAGE_COUNT;
        String stringPage = getParameter(requestContent, ParameterName.PAGE);
        int page = (stringPage == null) ? START_PAGE : Integer.parseInt(stringPage);
        int offset = (page - START_PAGE)*RECORDS_PAGE;
        VacancyService vacancyService = VacancyServiceImpl.getInstance();
        try {
            HashMap<Vacancy, Map.Entry<Location, UserInfo>> result = vacancyService.searchVacancyByCriteria(requestContent, offset, pageCount);
            if (result.isEmpty()) {
                requestContent.setNewValueInRequestAttributes("result_search_vacancy", "no result");
            } else {
                requestContent.setNewValueInRequestAttributes("result_search_vacancy", result);
                requestContent.setNewValueInRequestAttributes(ParameterName.PAGE, page);
                requestContent.setNewValueInRequestAttributes(ParameterName.PAGE_COUNT,"");
            }
            router.setType(Router.Type.FORWARD);
            router.setPage(PagePath.MAIN_PAGE);
        } catch (
                ServiceException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new CommandException(exception); // TODO
        }
        return router;
    }

    private String getParameter(RequestContent requestContent, String parameterName) {
        String[] parameters = requestContent.getRequestParameters().get(parameterName);
        if (parameters != null && parameters.length > 0 && parameters[0] != null) {
            return parameters[0];
        }
        return null;
    }
}
