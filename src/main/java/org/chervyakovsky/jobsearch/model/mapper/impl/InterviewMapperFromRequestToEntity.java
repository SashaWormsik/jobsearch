package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.ParameterName;
import org.chervyakovsky.jobsearch.model.entity.Interview;
import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntity;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * The type InterviewMapperFromRequestToEntity class.
 * Maps a set of parameters from the RequestContent object to the Interview class object.
 */
public class InterviewMapperFromRequestToEntity implements MapperFromRequestToEntity<Interview> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Interview map(RequestContent requestContent) {
        Interview interview = new Interview();
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        String[] id = requestParameters.get(ParameterName.INTERVIEW_ID);
        String[] date = requestParameters.get(ParameterName.INTERVIEW_APPOINTED_DATE);
        String[] status = requestParameters.get(ParameterName.INTERVIEW_STATUS);
        String[] vacancyId = requestParameters.get(ParameterName.INTERVIEW_VACANCY_ID);
        String[] workerId = requestParameters.get(ParameterName.INTERVIEW_WORKER_ID);
        String[] communicationMethod = requestParameters.get(ParameterName.INTERVIEW_COMMUNICATION_METHOD);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        apply(date, s -> {
            try {
                interview.setAppointedDateTime(simpleDateFormat.parse(s));
            } catch (ParseException exception) {
                LOGGER.log(Level.ERROR, exception);
            }
        });
        apply(id, s -> interview.setId(Long.parseLong(s)));
        apply(status, s -> interview.setInterviewStatus(InterviewStatus.getStatus(s)));
        apply(vacancyId, s -> interview.setVacancyId(Long.parseLong(s)));
        apply(workerId, s -> interview.setUserInfoId(Long.parseLong(s)));
        apply(communicationMethod, interview::setCommunicationMethod);
        return interview;
    }

    private void apply(String[] parameterValue, Consumer<String> consumer) {
        if (parameterValue != null && parameterValue.length > 0 && parameterValue[0] != null) {
            consumer.accept(parameterValue[0]);
        }
    }
}
