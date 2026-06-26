package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentControllerMapping {

    public AssignmentResponseDto assignmentResponseDto(Assignment assignment){
        return AssignmentResponseDto.builder()
                .assignmentId(assignment.getAssignmentId())
                .assignmentTitle(assignment.getAssignmentTitle())
                .assignmentDescription(assignment.getAssignmentDescription())
                .deadline(assignment.getDeadline())
                .build();
    }

}
