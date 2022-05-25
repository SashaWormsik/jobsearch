package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum InterviewStatus {
    PENDING, ASSIGNED, SUCCESSFUL, DENIED;


    public static InterviewStatus getStatus(String interviewStatus){ // fixme
        if(interviewStatus != null){
            return Arrays.stream(InterviewStatus.values()).
                    filter(interview -> interview.name().equals(interviewStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
