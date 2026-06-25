package com.coditas.cohorttheplatform.dto.course.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseRequestDto {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String courseName;

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String courseDescription;

}
