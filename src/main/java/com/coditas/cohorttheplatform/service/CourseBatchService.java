package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.EnrollmentResponseDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseBatchService {
    AddCourseMaterialResponseDto addMaterial(
            @NotNull Long courseId,
            @NotNull Long batchId,
            @Valid AddCourseMaterialRequestDto request,
            CohortUser user);

    GetAllCourseBatch getAllBatches(
            @NotNull Long courseId,
            int page,
            int size);

    EnrollmentResponseDto enrollInCourse(
            @NotNull Long courseId,
            @NotNull Long batchId,
            CohortUser user);
}
