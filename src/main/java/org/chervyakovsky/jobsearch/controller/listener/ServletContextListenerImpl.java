package org.chervyakovsky.jobsearch.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;
import org.chervyakovsky.jobsearch.util.DailyProcessForInterviews;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    /**
     * Causes a connection pool to be created and a background task to be started.
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new DailyProcessForInterviews(), 1, 1, TimeUnit.DAYS);
    }

    /**
     * Causes the connection pool to be destroyed and the background task to stop.
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdown();
        ConnectionPool.getInstance().destroyPool();
    }

}
