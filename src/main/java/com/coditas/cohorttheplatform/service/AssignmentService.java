package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.assignment.request.CreateAssignmentRequestDto;
import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;

public interface AssignmentService {
    AssignmentResponseDto createAssignment(
            Long batchId,
            @Valid CreateAssignmentRequestDto request,
            CohortUser user);
}
