package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.assignment.request.CreateAssignmentRequestDto;
import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.exception.AuthorizationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.AssignmentControllerMapping;
import com.coditas.cohorttheplatform.repository.AssignmentRepository;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import com.coditas.cohorttheplatform.repository.CourseBatchRepository;
import com.coditas.cohorttheplatform.service.AssignmentService;
import com.coditas.cohorttheplatform.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseBatchRepository courseBatchRepository;
    private final AssignmentControllerMapping assignmentControllerMapping;
    private final EmailService emailService;
    private final CohortUserRepository cohortUserRepository;


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


        List<String> emails = cohortUserRepository.getAllEmailIdByBatchId(batch.getCourseBatchId());

        try {
            emailService.assignmentUpload(emails);
        } catch (Exception ex) {
            log.error("Email Sending failed", ex);
        }


        return assignmentControllerMapping.assignmentResponseDto(assignment);

    }
}
