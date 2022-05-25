package org.chervyakovsky.jobsearch.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;


public class SessionFactory {
    private static final String USER_NAME_PROPERTIES = "mail.user.name";
    private static final String USER_PASSWORD_PROPERTIES = "mail.user.password";


    public static Session createSession(Properties properties) {
        String userName = properties.getProperty(USER_NAME_PROPERTIES);
        String userPassword = properties.getProperty(USER_PASSWORD_PROPERTIES);
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
