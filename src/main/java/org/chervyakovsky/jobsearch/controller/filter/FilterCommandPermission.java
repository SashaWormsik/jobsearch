package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.controller.command.CommandType;
import org.chervyakovsky.jobsearch.controller.filter.permission.UserPermissionCommand;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.io.IOException;
import java.util.Arrays;

/**
 * The filter defines valid commands according to the user's role.
 */
@WebFilter(filterName = "FilterCommandPermission", urlPatterns = "/controller")
public class FilterCommandPermission implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String commandString = httpServletRequest.getParameter(ParameterName.COMMAND);
        if (commandString == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        boolean existsCommand = Arrays.stream(CommandType.values())
                .anyMatch(commandType -> commandString.equalsIgnoreCase(commandType.name()));
        if (!existsCommand) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }
        CommandType commandType = CommandType.getCommandType(commandString);
        UserRoleStatus role = UserRoleStatus.GUEST;
        HttpSession session = httpServletRequest.getSession();
        UserInfo user = (UserInfo) session.getAttribute(AttributeName.USER);
        if (user != null) {
            role = user.getRole();
        }
        boolean isPermission = UserPermissionCommand.isPermitted(role, commandType);
        if (!isPermission) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
