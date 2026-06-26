package com.coditas.cohorttheplatform.service;


import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;

public interface StudyCoachService {


    String chat(
            @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK) String message,
            CohortUser user);
}
