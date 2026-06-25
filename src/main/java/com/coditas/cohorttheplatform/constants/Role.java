package com.coditas.cohorttheplatform.constants;

import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;


public enum Role {

    ADMIN,
    INSTRUCTOR,
    STUDENT;


    public static Role toValue(String role){

        for(Role r : Role.values()){
            if(role.equalsIgnoreCase(r.name())){
                return r;
            }
        }
        throw new NotFoundException(ExceptionMessages.INVALID_ROLE);
    }

}
