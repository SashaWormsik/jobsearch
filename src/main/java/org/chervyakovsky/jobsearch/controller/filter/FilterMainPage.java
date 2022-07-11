package org.chervyakovsky.jobsearch.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.VacancyDao;
import org.chervyakovsky.jobsearch.model.dao.impl.VacancyDaoImpl;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.util.Pageable;

import java.io.IOException;
import java.util.Map;

/**
 * Filter for filling the main page content.
 */
@WebFilter(filterName = "FilterMainPage", urlPatterns = "/pages/main.jsp",
        dispatcherTypes = DispatcherType.REQUEST )
public class FilterMainPage implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Pageable pageable = new Pageable();
        VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
        Location location = new Location();
        location.setCountry("");
        location.setCity("");
        Vacancy vacancy = new Vacancy();
        vacancy.setJobTitle("");
        Map<Vacancy, Map.Entry<Location, UserInfo>> result;
        try {
            result = vacancyDao.findByCriteria(vacancy, location, pageable);
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServletException(exception);
        }
        httpServletRequest.setAttribute(AttributeName.VACANCIES, result);
        httpServletRequest.setAttribute(ParameterName.PAGE, pageable.getPage());
        httpServletRequest.setAttribute(ParameterName.PAGE_COUNT, pageable.getPageCount());
        chain.doFilter(request, response);
    }
}
