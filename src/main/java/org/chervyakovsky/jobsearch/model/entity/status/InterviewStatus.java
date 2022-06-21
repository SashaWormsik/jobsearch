package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum InterviewStatus {
    IN_WAITING, IS_SCHEDULED, IS_REJECTED, IS_COMPLETED;


    public static InterviewStatus getStatus(String interviewStatus){
        if(interviewStatus != null){
            return Arrays.stream(InterviewStatus.values()).
                    filter(interview -> interview.name().equals(interviewStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
