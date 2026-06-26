package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<CourseDetailsDto> getAllCourses(
            int page,
            int size);


}
