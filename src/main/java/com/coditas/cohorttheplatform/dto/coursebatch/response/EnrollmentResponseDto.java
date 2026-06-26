package com.coditas.cohorttheplatform.dto.coursebatch.response;

import com.coditas.cohorttheplatform.dto.common.BatchDetailsDto;
import com.coditas.cohorttheplatform.dto.common.CohortUserDetailsDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDto {

    private Long enrollmentId;
    private LocalDate enrolledAt;
    private CourseDetailsDto courseDetails;
    private BatchDetailsDto batchDetails;
    private CohortUserDetailsDto studentDetails;

}
