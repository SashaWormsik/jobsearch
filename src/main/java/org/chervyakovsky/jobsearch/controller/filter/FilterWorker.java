package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;

import java.io.IOException;

@WebFilter(filterName = "FilterWorker", urlPatterns = "/pages/worker/*")
public class FilterWorker implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute(AttributeName.USER);
            if (userInfo != null) {
                if (userInfo.getRole() == UserRoleStatus.WORKER) {
                    chain.doFilter(request, response);
                } else {
                    httpServletResponse.sendError(403);
                }
            } else {
                httpServletResponse.sendError(401);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
