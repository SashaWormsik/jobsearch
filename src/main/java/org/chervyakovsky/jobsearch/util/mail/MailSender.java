package org.chervyakovsky.jobsearch.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SUBJECT = "Confirmation of actions on the site";
    private static final String EMAIL_FROM = "alexworms2001@mail.ru";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";


    private MimeMessage massage;
    private String sendToEmail;
    private String text;
    private Properties properties;

    public MailSender(String sendToEmail, String text, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.text = text;
        this.properties = properties;
    }

    public void send() {
        try {
            initMessage();
            Transport.send(massage);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, e);
        }

    }

    private void initMessage() throws MessagingException {
        Session session = SessionFactory.createSession(properties);
        session.setDebug(true);
        massage = new MimeMessage(session);
        massage.setSubject(SUBJECT);
        massage.setFrom(EMAIL_FROM);
        massage.setContent(text, CONTENT_TYPE);
        massage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

}
