package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "FilterEncoding", urlPatterns = "/*")
public class FilterEncoding implements Filter {
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType(CONTENT_TYPE);
        httpServletRequest.setCharacterEncoding(ENCODING_UTF_8);
        httpServletResponse.setCharacterEncoding(ENCODING_UTF_8);
        chain.doFilter(request, response);
    }
}
