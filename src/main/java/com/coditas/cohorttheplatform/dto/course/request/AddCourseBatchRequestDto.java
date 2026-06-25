package com.coditas.cohorttheplatform.dto.course.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseBatchRequestDto {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String batchName;

    @NotNull(message = ValidationMessages.FIELD_NOT_BLANK)
    private Long enrollmentLimit;

    @NotNull(message = ValidationMessages.FIELD_NOT_BLANK)
    private Long instructorId;

    @NotNull(message = ValidationMessages.FIELD_NOT_BLANK)
    private LocalDate startDate;

    @NotNull(message = ValidationMessages.FIELD_NOT_BLANK)
    private LocalDate endDate;

}
