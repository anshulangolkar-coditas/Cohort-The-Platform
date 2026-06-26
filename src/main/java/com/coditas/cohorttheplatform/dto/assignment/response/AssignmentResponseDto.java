package com.coditas.cohorttheplatform.dto.assignment.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentResponseDto {

    private Long assignmentId;
    private String assignmentTitle;
    private String assignmentDescription;
    private LocalDateTime deadline;

}
