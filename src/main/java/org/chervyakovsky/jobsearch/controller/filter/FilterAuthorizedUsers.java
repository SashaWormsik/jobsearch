package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.PagePath;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;

import java.io.IOException;

@WebFilter(filterName = "FilterAuthorizedUsers",
        urlPatterns = {"/pages/login.jsp", "/pages/registration.jsp", "/pages/forgot_password.jsp", "/pages/reset_password.jsp"})
public class FilterAuthorizedUsers implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute(AttributeName.USER);
            if (userInfo == null) {
                chain.doFilter(request, response);
            } else {
                String path = httpServletRequest.getContextPath().concat("/").concat(PagePath.MAIN_PAGE);
                httpServletResponse.sendRedirect(path);
            }
        }else {
            chain.doFilter(request, response);
        }
    }
}
