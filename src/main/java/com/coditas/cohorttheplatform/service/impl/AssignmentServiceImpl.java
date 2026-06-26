package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.assignment.request.CreateAssignmentRequestDto;
import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.exception.AuthorizationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.exception.ResourceNotFoundException;
import com.coditas.cohorttheplatform.mappings.AssignmentControllerMapping;
import com.coditas.cohorttheplatform.repository.AssignmentRepository;
import com.coditas.cohorttheplatform.repository.CourseBatchRepository;
import com.coditas.cohorttheplatform.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseBatchRepository courseBatchRepository;
    private final AssignmentControllerMapping assignmentControllerMapping;


    @Override
    public AssignmentResponseDto createAssignment(
            Long batchId,
            CreateAssignmentRequestDto request,
            CohortUser user) {

    CourseBatch batch = courseBatchRepository.findById(batchId)
            .orElseThrow(() -> new NotFoundException(ExceptionMessages.BATCH_NOT_FOUND));

        if (!batch.getInstructor().getUserId().equals(user.getUserId())) {
            throw new AuthorizationException(ExceptionMessages.NOT_ALLOWED);
        }

        Assignment assignment = assignmentRepository.save(Assignment.builder()
                .courseBatch(batch)
                .assignmentTitle(request.getAssignmentTitle())
                .assignmentDescription(request.getAssignmentDescription())
                .deadline(request.getDeadline())
                .uploadedBy(user)
                .build());

        return assignmentControllerMapping.assignmentResponseDto(assignment);

    }
}
