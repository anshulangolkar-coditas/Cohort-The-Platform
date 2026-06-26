package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.assignment.request.CreateAssignmentRequestDto;
import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.AssignmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/batches/{batchId}")
    public ResponseEntity<ApplicationResponse<AssignmentResponseDto>> createAssignment(
            @NotNull @PathVariable Long batchId,
            @Valid @RequestBody CreateAssignmentRequestDto request,
            @AuthenticationPrincipal CohortUser user) {

        AssignmentResponseDto response =
                assignmentService.createAssignment(batchId, request, user);

        return ResponseEntity.ok((ApplicationResponse.success(HttpStatus.CREATED.value(),
                        "Assignment created successfully",
                        response)));
    }



}
