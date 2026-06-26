package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.dto.student.response.CourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<CourseDetailsDto> getAllCourses(
            int page,
            int size);


    Page<AssignmentResponseDto> getAssignedAssignments(
            @NotNull Long batchId,
            CohortUser student, int page, int size);

    Page<CourseMaterialResponseDto> getAllMaterials(
            Long courseId,
            CohortUser user,
            int page,
            int size);
}
