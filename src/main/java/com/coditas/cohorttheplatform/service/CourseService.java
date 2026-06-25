package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;

public interface CourseService {
    AddCourseResponseDto addCourse(
            @Valid AddCourseRequestDto request,
            CohortUser user);
}
