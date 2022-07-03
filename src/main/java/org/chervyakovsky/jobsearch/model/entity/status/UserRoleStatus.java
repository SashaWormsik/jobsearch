package org.chervyakovsky.jobsearch.model.entity.status;

import java.util.Arrays;

public enum UserRoleStatus {
    ADMIN, COMPANY, WORKER, GUEST;


    public static UserRoleStatus getStatus(String userRoleStatus){
        UserRoleStatus defaultRole = UserRoleStatus.GUEST;
        if(userRoleStatus != null){
            return Arrays.stream(UserRoleStatus.values()).
                    filter(role -> role.name().equals(userRoleStatus.toUpperCase())).
                    findFirst().orElse(defaultRole);
        }
        return defaultRole;
    }
}
