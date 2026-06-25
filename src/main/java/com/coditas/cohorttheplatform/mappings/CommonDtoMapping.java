package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.common.CohortUserDetails;
import com.coditas.cohorttheplatform.entity.CohortUser;
import org.springframework.stereotype.Component;

@Component
public class CommonDtoMapping {

    public CohortUserDetails cohortUserDetails(CohortUser user){
        return CohortUserDetails.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .emailId(user.getEmail())
                .build();
    }

}
