package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.filter.permission.UserPermissionPage;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.io.IOException;

/**
 * The filter determines the valid pages according to the user's role.
 */
@WebFilter(filterName = "FilterPagePermission", urlPatterns = {"/pages/*"})
public class FilterPagePermission implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String page = httpServletRequest.getServletPath().substring(1);
        UserRoleStatus role = UserRoleStatus.GUEST;
        HttpSession session = httpServletRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(AttributeName.USER);
        if (userInfo != null) {
            role = userInfo.getRole();
        }
        if (role.equals(UserRoleStatus.GUEST) && !UserPermissionPage.isPermitted(role, page)) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if (!UserPermissionPage.isPermitted(role, page)) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
