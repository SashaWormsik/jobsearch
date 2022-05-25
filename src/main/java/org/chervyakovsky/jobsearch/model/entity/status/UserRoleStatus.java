package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum UserRoleStatus {
    ADMIN, COMPANY, WORKER;


    public static UserRoleStatus getStatus(String userRoleStatus){ // fixme
        if(userRoleStatus != null){
            return Arrays.stream(UserRoleStatus.values()).
                    filter(role -> role.name().equals(userRoleStatus.toUpperCase())).
                    findFirst().orElse(null);
        }
        return null;
    }
}
