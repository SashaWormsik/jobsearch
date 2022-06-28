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
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class ResetPasswordCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Router router = new Router();
        try {
            Optional<UserInfo> optionalUserInfo = userService.findUserByToken(requestContent);
            if (optionalUserInfo.isPresent()) {
                String token = optionalUserInfo.get().getUserToken();
                requestContent.setNewValueInRequestAttributes(ParameterName.USER_TOKEN ,token);
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.RESET_PASSWORD_PAGE);
            } else {
                requestContent.setNewValueInRequestAttributes("" , true); // TODO сообщение о том что юзер с токеном не найден
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.FORGOT_PASSWORD_PAGE);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
