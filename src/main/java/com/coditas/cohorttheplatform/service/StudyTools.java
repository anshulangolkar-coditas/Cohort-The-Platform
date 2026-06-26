package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.student.request.AssignmentSubmitRequestDto;
import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.CohortUser;

import java.util.List;

public interface StudyTools {

    //List<Assignment> getDueAssignments(Long studentId);
    //String getProgress(Long studentId);
    String submit(Long assignmentId, AssignmentSubmitRequestDto file, CohortUser user);
    String enroll(Long courseId, Long batchId ,CohortUser user);

}
