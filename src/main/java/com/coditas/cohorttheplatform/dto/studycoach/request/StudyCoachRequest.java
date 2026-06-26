package com.coditas.cohorttheplatform.dto.studycoach.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCoachRequest {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String message;

}
