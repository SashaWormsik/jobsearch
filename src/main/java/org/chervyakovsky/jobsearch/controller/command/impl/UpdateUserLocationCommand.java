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
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.LocationService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.LocationServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class UpdateUserLocationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        LocationService locationService = LocationServiceImpl.getInstance();
        try {
            Optional<UserInfo> optionalUserInfo = userService.findUserById(requestContent);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                Optional<Location> optionalLocation = locationService.locationIsPresent(requestContent);
                if (!optionalLocation.isPresent()) {
                    optionalLocation = locationService.save(requestContent);
                }
                if (optionalLocation.isPresent()) {
                    userInfo.setLocationId(optionalLocation.get().getId());
                    userService.updateUser(userInfo);
                    requestContent.setNewValueInSessionAttribute(AttributeName.USER, userInfo);
                    requestContent.setNewValueInSessionAttribute(AttributeName.USER_LOCATION, optionalLocation.get());
                    router.setType(Router.Type.REDIRECT);
                    router.setPage(PagePath.ACCOUNT_PAGE);
                }else {
                    requestContent.setParameterInAttribute();
                    router.setPage(PagePath.UPDATE_INFO_PAGE);
                }
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new CommandException(exception);
        }
        return router;
    }
}
