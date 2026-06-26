package com.coditas.cohorttheplatform.dto.assignment.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAssignmentRequestDto {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String assignmentTitle;

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String assignmentDescription;

    @NotNull(message = ValidationMessages.FIELD_NOT_BLANK)
    private LocalDateTime deadline;
}
