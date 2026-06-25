package com.coditas.cohorttheplatform.dto.course.response;

import com.coditas.cohorttheplatform.dto.common.CohortUserDetailsDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseResponseDto {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private CohortUserDetailsDto createdBy;

}
