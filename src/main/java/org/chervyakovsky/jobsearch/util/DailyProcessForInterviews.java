package org.chervyakovsky.jobsearch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.exception.DaoException;
import org.chervyakovsky.jobsearch.model.dao.InterviewDao;
import org.chervyakovsky.jobsearch.model.dao.impl.InterviewDaoImpl;

/**
 * The DailyProcessForInterviews util class.
 * Launches a background task to change the status of unclaimed interviews.
 */
public class DailyProcessForInterviews implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void run() {
        InterviewDao interviewDao = InterviewDaoImpl.getInstance();
        try {
            interviewDao.editOverdueInterviews();
        } catch (DaoException exception) {
            LOGGER.log(Level.ERROR, exception);
        }
    }
}
