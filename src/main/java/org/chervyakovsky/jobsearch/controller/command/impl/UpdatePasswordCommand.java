package org.chervyakovsky.jobsearch.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.controller.Router;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.service.CredentialService;
import org.chervyakovsky.jobsearch.model.service.UserService;
import org.chervyakovsky.jobsearch.model.service.impl.CredentialServiceImpl;
import org.chervyakovsky.jobsearch.model.service.impl.UserServiceImpl;

import java.util.Optional;

public class UpdatePasswordCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(RequestContent requestContent) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        CredentialService credentialService = CredentialServiceImpl.getInstance();
        Router router = new Router();
        try {
            Optional<UserInfo> optionalUserInfo = userService.findUserByToken(requestContent);
            if (optionalUserInfo.isPresent()) {
                UserInfo userInfo = optionalUserInfo.get();
                userInfo.setUserToken(null);
                if (credentialService.updatePasswordByUserId(requestContent, userInfo.getId()) && userService.updateUser(userInfo)) {
                    // TODO добавить сообщение об успешной смене пароля
                    router.setType(Router.Type.REDIRECT);
                    router.setPage(PagePath.LOGIN_PAGE);
                } else {
                    // TODO что-то пошло не так при смене пароля
                    router.setType(Router.Type.FORWARD);
                    router.setPage(PagePath.RESET_PASSWORD_PAGE);
                }
            } else {
                // TODO сообщение что юзер не найден по токену
                router.setType(Router.Type.FORWARD);
                router.setPage(PagePath.RESET_PASSWORD_PAGE);
            }
        } catch (ServiceException exception) {
            LOGGER.log(Level.ERROR, exception); // TODO
            throw new CommandException(exception); // TODO
        }
        return router;
    }
}
