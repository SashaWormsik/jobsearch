package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.controller.command.CommandType;
import org.chervyakovsky.jobsearch.controller.filter.permission.UserPermission;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.io.IOException;

@WebFilter(filterName = "FilterCommandPermission", urlPatterns = "/controller")
public class FilterCommandPermission implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String commandString = httpServletRequest.getParameter(ParameterName.COMMAND);
        if (commandString == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        CommandType commandType = CommandType.getCommandType(commandString);
        UserRoleStatus role = UserRoleStatus.GUEST;
        UserInfo user = (UserInfo) session.getAttribute(AttributeName.USER);
        if (user != null) {
            role = user.getRole();
        }
        boolean isPermission = UserPermission.isPermitted(role, commandType);
        if (!isPermission) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
