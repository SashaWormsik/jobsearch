package org.chervyakovsky.jobsearch.controller.command.impl;

import org.chervyakovsky.jobsearch.controller.*;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

/**
 * The {@link Command} that sets localization to the selected language.
 *
 * @see org.chervyakovsky.jobsearch.controller.command.Command
 */
public class SetLocalizationTypeCommand implements Command {

    private static final String CONTROLLER = "/jobsearch_war_exploded/controller?";

    /**
     * Executes a command.
     *
     * @param requestContent A {@link RequestContent} object that contains request parameters, request and
     *                       session attributes and the session itself
     * @return The router with type {@link Router.Type#REDIRECT} to previous page or command.
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        String page = requestContent.getParameterFromRequest(ParameterName.PAGE_PATH);
        String queryString = requestContent.getParameterFromRequest(ParameterName.QUERY_STRING);
        String localeRequest = requestContent.getParameterFromRequest(ParameterName.LOCALE);
        if (queryString != null && !queryString.isEmpty()) {
            page = CONTROLLER.concat(queryString);
        } else if (page == null) {
            page = PagePath.MAIN_PAGE;
        }
        if (localeRequest != null && localeRequest.equals(LocaleType.ENGLISH)) {
            localeRequest = LocaleType.ENGLISH;
        } else {
            localeRequest = LocaleType.RUSSIAN;
        }
        requestContent.setNewValueInSessionAttribute(AttributeName.LOCALE, localeRequest);
        router.setPage(page);
        router.setType(Router.Type.REDIRECT);
        return router;
    }
}
