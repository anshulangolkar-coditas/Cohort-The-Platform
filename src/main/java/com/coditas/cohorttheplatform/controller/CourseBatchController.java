package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.EnrollmentResponseDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.CourseBatchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseBatchController {

    private final CourseBatchService courseBatchService;

    @PostMapping("/{courseId}/batches/{batchId}/materials")
    public ResponseEntity<ApplicationResponse<AddCourseMaterialResponseDto>> addMaterial(
            @NotNull @PathVariable Long courseId,
            @NotNull @PathVariable Long batchId,
            @Valid @ModelAttribute AddCourseMaterialRequestDto request,
            @AuthenticationPrincipal CohortUser user) {

        AddCourseMaterialResponseDto details = courseBatchService.addMaterial(courseId, batchId, request, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "Materials added successfully", details));
    }

    @GetMapping("/{courseId}/batches")
    public ResponseEntity<ApplicationResponse<GetAllCourseBatch>> getAllBatches(
            @NotNull @PathVariable Long courseId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "8") int size){

        GetAllCourseBatch batchList = courseBatchService.getAllBatches(courseId, page, size);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.OK.value(),
                "Fetched all the batches successfully", batchList));
    }

    @PostMapping("/{courseId}/batches/{batchId}/enroll")
    public ResponseEntity<ApplicationResponse<EnrollmentResponseDto>> enrollInCourse(
            @NotNull @PathVariable Long courseId,
            @NotNull @PathVariable Long batchId,
            @AuthenticationPrincipal CohortUser user){

        EnrollmentResponseDto details = courseBatchService.enrollInCourse(courseId, batchId, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "Enrolled in batch successfully", details));
    }





}
