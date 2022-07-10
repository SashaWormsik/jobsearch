package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum WorkingStatus {
    WORK, IN_SEARCH, NO_STATUS;


    public static WorkingStatus getStatus(String workingStatus){
        if(workingStatus != null){
            return Arrays.stream(WorkingStatus.values()).
                    filter(workingS -> workingS.name().equals(workingStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
