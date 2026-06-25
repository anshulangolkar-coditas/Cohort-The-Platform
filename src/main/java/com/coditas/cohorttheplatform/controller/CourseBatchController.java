package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.CourseBatchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseBatchController {

    private final CourseBatchService courseBatchService;

    @PostMapping("/{courseId}/batches/{batchId}/materials")
    public ResponseEntity<ApplicationResponse<AddCourseMaterialResponseDto>> addMaterial(
            @NotNull @PathVariable Long courseId,
            @NotNull @PathVariable Long batchId,
            @Valid @RequestBody AddCourseMaterialRequestDto request,
            @RequestParam MultipartFile file,
            @AuthenticationPrincipal CohortUser user){

        AddCourseMaterialResponseDto details = courseBatchService.addMaterial(courseId, batchId, request, file, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "Materials added successfully", details));
    }


}
