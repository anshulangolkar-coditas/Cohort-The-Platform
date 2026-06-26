package com.coditas.cohorttheplatform.dto.assignment.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAssignmentRequestDto {

    String assignmentTitle;
    String assignmentDescription;
    LocalDateTime deadline;
    Long batchId;

}
