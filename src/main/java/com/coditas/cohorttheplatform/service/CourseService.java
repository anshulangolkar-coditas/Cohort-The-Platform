package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.course.request.AddCourseBatchRequestDto;
import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseBatchResponseDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CourseService {
    AddCourseResponseDto addCourse(
            @Valid AddCourseRequestDto request,
            CohortUser user);

    AddCourseBatchResponseDto addCourseBatch(
            @NotNull Long courseId,
            @Valid AddCourseBatchRequestDto request,
            CohortUser user);
}
