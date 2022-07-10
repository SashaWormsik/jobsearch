package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum WorkExperienceStatus {
    WITHOUT, UP_TO_A_YEAR, FROM_1_TO_3_YEARS, FROM_3_TO_5_YEARS, MORE_THAN_5_YEARS;

    public static WorkExperienceStatus getStatus(String workExperienceStatus){
        if(workExperienceStatus != null){
            return Arrays.stream(WorkExperienceStatus.values()).
                    filter(workExperience -> workExperience.name().equals(workExperienceStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
