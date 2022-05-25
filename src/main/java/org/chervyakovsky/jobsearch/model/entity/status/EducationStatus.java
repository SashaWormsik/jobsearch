package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum EducationStatus {

    HIGHER, SECONDARY, BASIC, NO_EDUCATION, NOT_SPECIFIED;


    public static EducationStatus getStatus(String educationStatus){ // fixme
        if(educationStatus != null){
            return Arrays.stream(EducationStatus.values()).
                    filter(education -> education.name().equals(educationStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
