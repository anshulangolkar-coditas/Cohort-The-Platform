package com.coditas.cohorttheplatform.dto.coursebatch.response;

import com.coditas.cohorttheplatform.dto.common.CohortUserDetailsDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionResponseDto {

    private Long submissionId;
    private String fileName;
    private LocalDateTime submittedAt;
    private CohortUserDetailsDto studentDetails;

}
