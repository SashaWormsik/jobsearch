package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;

/**
 * The filter moves temporary session attributes to the request.
 */
@WebFilter(filterName = "FilterRedirect", urlPatterns = "/*", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class FilterRedirect implements Filter {
    private static final String TEMP_ATTR_PREFIX = "temp_";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            Enumeration<String> nameSessionAttribute = session.getAttributeNames();
            while (nameSessionAttribute.hasMoreElements()) {
                String name = nameSessionAttribute.nextElement();
                if (name.startsWith(TEMP_ATTR_PREFIX)) {
                    httpRequest.setAttribute(name, session.getAttribute(name));
                    session.removeAttribute(name);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
