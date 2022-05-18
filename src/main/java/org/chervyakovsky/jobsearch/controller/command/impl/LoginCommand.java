package org.chervyakovsky.jobsearch.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.*;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.service.LocationService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.LocationServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            Optional<UserInfo> optionalUserInfo = userService.authenticate(login, password);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                session.setAttribute(AttributeName.USER, userInfo);
                session.setAttribute(AttributeName.USER_LOCATION, getUserLocation(userInfo)); // fixme
                router.setPage(PagePath.MAIN_PAGE);
                router.setType(Router.Type.REDIRECT); // fixme
            } else {
                request.setAttribute(AttributeName.KEY_MESSAGE, MessageName.INCORRECT_LOGIN_PASSWORD);
                router.setPage(PagePath.LOGIN_PAGE);
                router.setType(Router.Type.FORWARD);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }

    private Location getUserLocation(UserInfo userInfo) throws ServiceException {  // fixme
        LocationService locationService = LocationServiceImpl.getInstance();
        Optional<Location> optionalLocation = locationService.findUserLocation(userInfo);
        return optionalLocation.orElse(null);  // fixme
    }
}
