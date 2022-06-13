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
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class UpdateUserInfo implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<UserInfo> optionalUserInfo = userService.findUserById(requestContent);
            if (optionalUserInfo.isPresent()){
                UserInfo userInfo = optionalUserInfo.get();
                if(userService.updateUser(userInfo, requestContent)){
                    requestContent.setNewValueInSessionAttribute(AttributeName.USER ,userInfo);
                    router.setType(Router.Type.REDIRECT);
                }else {
                    router.setType(Router.Type.FORWARD);
                }
                router.setPage(PagePath.USER_PROFILE_PAGE);
            }else {
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.USER_INTERVIEW_PAGE); // todo исправить адрес
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new CommandException(exception); // TODO
        }
        return router;
    }
}
