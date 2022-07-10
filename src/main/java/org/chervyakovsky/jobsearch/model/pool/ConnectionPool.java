package org.chervyakovsky.jobsearch.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type ConnectionPool class. Announces two limited thread - safe queues
 * for free and taken connections. It is a singleton class with private
 * constructor and static method to initialize this class.
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DATABASE_PROPERTIES_FILE = "database";
    private static final String PROPERTY_POOL_SIZE = "pool_size";
    private static final int DEFAULT_POOL_SIZE = 8;
    private static int POOL_SIZE;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final Lock creationLock = new ReentrantLock(true);

    private static ConnectionPool instance;

    private BlockingQueue<ProxyConnection> freeConnection;
    private BlockingQueue<ProxyConnection> usedConnection;


    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTIES_FILE);
            if (resourceBundle.containsKey(PROPERTY_POOL_SIZE)) {
                POOL_SIZE = Integer.parseInt(resourceBundle.getString(PROPERTY_POOL_SIZE));
            } else {
                POOL_SIZE = DEFAULT_POOL_SIZE;
            }
        } catch (MissingResourceException exception) {
            LOGGER.log(Level.ERROR, exception);
        }
    }

    private ConnectionPool() {
        freeConnection = new LinkedBlockingDeque<>(POOL_SIZE);
        usedConnection = new LinkedBlockingDeque<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection tempConnection = ConnectionFactory.getConnection();
                ProxyConnection tempProxyConnection = new ProxyConnection(tempConnection);
                freeConnection.add(tempProxyConnection);
            } catch (SQLException exception) {
                LOGGER.log(Level.ERROR, "Error connecting to the database: " + exception.getMessage());
            }
        }
        if (freeConnection.isEmpty()) {
            LOGGER.log(Level.FATAL, "FATAL ERROR! No database connections have been created");
            throw new RuntimeException("FATAL ERROR! No database connections have been created");
        }
        LOGGER.log(Level.INFO, "{} connections have been created", freeConnection.size());
    }

    /**
     * Get instance connection pool.
     *
     * @return the connection pool
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                creationLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                creationLock.unlock();
            }
        }
        return instance;
    }

    /**
     * Get connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnection.take();
            usedConnection.put(proxyConnection);
        } catch (InterruptedException exception) {
            LOGGER.log(Level.ERROR, "Error when receiving a connection from a pool: " + exception.getMessage());
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    /**
     * Release connection. The connection returns
     * to the pool.
     *
     * @param connection the connection
     */
    public boolean releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            try {
                usedConnection.remove(connection);
                freeConnection.put((ProxyConnection) connection);
                return true;
            } catch (InterruptedException exception) {
                LOGGER.log(Level.ERROR, "Error when releasing the connection: " + exception.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    /**
     * Destroy pool and deregister drivers.
     */
    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException | InterruptedException exception) {
                LOGGER.error("Error when destroying the connection pool: " + exception.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.log(Level.INFO, "Deregistering jdbc driver: {}", driver);
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error deregistering driver: {}", driver, e);
            }
        }
    }
}

