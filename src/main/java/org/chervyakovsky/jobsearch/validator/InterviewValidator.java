package org.chervyakovsky.jobsearch.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.AttributeName;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterviewValidator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String COMMUNICATION_REGEX = "[}{><]";

    private static InterviewValidator instance;

    private InterviewValidator() {
    }

    public static InterviewValidator getInstance() {
        if (instance == null) {
            instance = new InterviewValidator();
        }
        return instance;
    }

    public boolean validateAppointedDate(Date date) {
        Date validDate = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        if (date == null) {
            return false;
        }
        return date.after(validDate);
    }

    public boolean validateCommunicationMethod(String communicationMethod) {
        if (communicationMethod == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(COMMUNICATION_REGEX);
        Matcher matcher = pattern.matcher(communicationMethod);
        return !matcher.find();
    }

    public boolean isValidInterviewData(RequestContent requestContent) throws ServiceException {
        boolean result = true;
        String communicationMethod = requestContent.getParameterFromRequest(ParameterName.INTERVIEW_COMMUNICATION_METHOD);
        String stringDate = requestContent.getParameterFromRequest(ParameterName.INTERVIEW_APPOINTED_DATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            if (!validateAppointedDate(simpleDateFormat.parse(stringDate))) {
                requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_DATE, true);
                result = false;
            }
            if (!validateCommunicationMethod(communicationMethod)) {
                requestContent.setNewValueInRequestAttributes(AttributeName.INCORRECT_COMMUNICATION_METHOD, true);
                result = false;
            }
        } catch (ParseException exception) {
            LOGGER.log(Level.ERROR, exception);
            throw new ServiceException();
        }
        return result;
    }
}
