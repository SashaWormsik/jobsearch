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
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.LocationService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.LocationServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        requestContent.getSession().removeAttribute(AttributeName.SUCCESSFUL_REGISTRATION); // fixme
        UserService userService = UserServiceImpl.getInstance();
        Router router = new Router();
        try {
            Optional<UserInfo> optionalUserInfo = userService.authenticate(requestContent);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                if (userInfo.getUserStatus()) {
                    requestContent.setNewValueInSessionAttribute(AttributeName.USER, userInfo);
                    Location userLocation = getUserLocation(userInfo);
                    requestContent.setNewValueInSessionAttribute(AttributeName.USER_LOCATION, userLocation);
                    router.setPage(PagePath.MAIN_PAGE);
                    router.setType(Router.Type.REDIRECT);
                }else{
                    requestContent.setNewValueInSessionAttribute(AttributeName.ACCOUNT_IS_BLOCKED, true);
                    router.setPage(PagePath.LOGIN_PAGE);
                    router.setType(Router.Type.FORWARD);
                }
            } else {
                requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_LOGIN_OR_PASSWORD, true);
                router.setPage(PagePath.LOGIN_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }

    private Location getUserLocation(UserInfo userInfo) throws ServiceException {
        LocationService locationService = LocationServiceImpl.getInstance();
        Optional<Location> optionalLocation = locationService.findUserLocation(userInfo);
        return optionalLocation.orElse(null);
    }
}
