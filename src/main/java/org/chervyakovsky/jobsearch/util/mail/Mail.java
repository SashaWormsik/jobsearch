package org.chervyakovsky.jobsearch.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mail {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String EMAIL_PROPERTIES = "email.properties";

    public static void sendMail(String sendMailTo, String text)  {
        Properties properties = new Properties();
        try (InputStream inputStream = Mail.class.getClassLoader()
                .getResourceAsStream(EMAIL_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e);
        }
        MailSender mailSender = new MailSender(sendMailTo, text, properties);
        mailSender.send();
    }
}
