package org.chervyakovsky.jobsearch.model.service;

import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.entity.Location;
import org.chervyakovsky.jobsearch.model.entity.UserInfo;
import org.chervyakovsky.jobsearch.model.entity.Vacancy;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.util.HashMap;
import java.util.Map;

public interface VacancyService {

    boolean createNewVacancy(RequestContent requestContent) throws ServiceException;

    boolean updateVacancy(RequestContent requestContent) throws ServiceException;

    HashMap<Vacancy, Map.Entry<Location, UserInfo>> searchVacancyByCriteria(RequestContent requestContent, int offset, Integer pageCount) throws ServiceException;

}
