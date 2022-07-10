package org.chervyakovsky.jobsearch.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The type connection factory.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DATABASE_PROPERTIES_FILE = "database";
    private static final String USER_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String URL_PROPERTY = "url";
    private static final String DRIVER_PROPERTY = "driver";
    private static final String USER;
    private static final String PASSWORD;
    private static final String URL;
    private static final String DRIVER;


    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTIES_FILE);
        if (resourceBundle.containsKey(USER_PROPERTY)) {
            USER = resourceBundle.getString(USER_PROPERTY);
        } else {
            LOGGER.fatal("Error reading the value of the \"USER\" property from the settings file");
            throw new RuntimeException("Error reading the value of the \"USER\" property from the settings file");
        }
        if (resourceBundle.containsKey(PASSWORD_PROPERTY)) {
            PASSWORD = resourceBundle.getString(PASSWORD_PROPERTY);
        } else {
            LOGGER.fatal("Error reading the value of the \"PASSWORD\" property from the settings file");
            throw new RuntimeException("Error reading the value of the \"PASSWORD\" property from the settings file");
        }
        if (resourceBundle.containsKey(URL_PROPERTY)) {
            URL = resourceBundle.getString(URL_PROPERTY);
        } else {
            LOGGER.fatal("Error reading the value of the \"URL\" property from the settings file");
            throw new RuntimeException("Error reading the value of the \"URL\" property from the settings file");
        }
        if (resourceBundle.containsKey(DRIVER_PROPERTY)) {
            DRIVER = resourceBundle.getString(DRIVER_PROPERTY);
        } else {
            LOGGER.fatal("Error reading the value of the \"DRIVER\" property from the settings file");
            throw new RuntimeException("Error reading the value of the \"DRIVER\" property from the settings file");
        }
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException exception) {
            LOGGER.fatal("Driver {} wasn't found: {}", DRIVER, exception);
            throw new RuntimeException("Driver " + DRIVER + " wasn't found: ", exception);
        }
    }

    private ConnectionFactory() {
    }

    /**
     * Create connection.
     *
     * @return the proxy connection
     * @throws SQLException the sql exception
     */
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

