package com.coditas.cohorttheplatform.dto.course.response;

import com.coditas.cohorttheplatform.dto.common.CohortUserDetailsDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseBatchResponseDto {

    private Long batchId;
    private String batchName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdAt;
    private CohortUserDetailsDto instructorDetails;
    private CourseDetailsDto courseDetails;

}
