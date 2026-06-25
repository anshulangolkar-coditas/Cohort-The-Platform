package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface CourseBatchService {
    AddCourseMaterialResponseDto addMaterial(
            @NotNull Long courseId,
            @NotNull Long batchId,
            @Valid AddCourseMaterialRequestDto request,
            MultipartFile file,
            CohortUser user);
}
