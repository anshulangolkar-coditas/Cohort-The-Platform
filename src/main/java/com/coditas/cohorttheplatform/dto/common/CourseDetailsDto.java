package com.coditas.cohorttheplatform.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetailsDto {

    private Long courseId;
    private String courseName;
    private String courseDescription;

}
