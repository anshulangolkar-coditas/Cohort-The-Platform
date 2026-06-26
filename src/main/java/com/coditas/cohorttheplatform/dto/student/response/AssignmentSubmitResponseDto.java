package com.coditas.cohorttheplatform.dto.student.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentSubmitResponseDto {

    private Long submissionId;
    private String fileName;
    private LocalDateTime submittedOn;

}
