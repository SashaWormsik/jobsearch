package org.chervyakovsky.jobsearch.util.mail;

public class MailMessageBuilder {

    private static final String LINK = "http://localhost:8080/jobsearch_war_exploded/controller?command=activate_user&token=";

    public static String buildMessageContent(String token) {
        StringBuilder builder = new StringBuilder();
        builder.append("<p>Hello!!!</p>")
                .append("<p>You requested an action.</p>")
                .append("<p>Click on the link below to confirm your action:</p>")
                .append("<p><a href=\"")
                .append(LINK).append(token)
                .append("\">CONFIRM</a></p>")
                .append("<br>")
                .append("<p>Ignore this email if you haven't made the request on the site</p>");
        return builder.toString();
    }

}
