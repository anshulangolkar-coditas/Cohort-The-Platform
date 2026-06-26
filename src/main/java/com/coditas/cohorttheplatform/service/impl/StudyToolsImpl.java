package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.student.request.AssignmentSubmitRequestDto;
import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.repository.AssignmentRepository;
import com.coditas.cohorttheplatform.repository.SubmissionRepository;
import com.coditas.cohorttheplatform.service.CourseBatchService;
import com.coditas.cohorttheplatform.service.StudentService;
import com.coditas.cohorttheplatform.service.StudyTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyToolsImpl implements StudyTools {

    private final AssignmentRepository assignmentRepository;
    //private final SubmissionRepository submissionRepository;
    private final StudentService studentService;
    private final CourseBatchService courseBatchService;

/*    @Override
    @Tool(name = "get_due_assignments")
    public List<Assignment> getDueAssignments(Long studentId) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusDays(7);

        return assignmentRepository.findDueAssignments(studentId, now, nextWeek);
    }*/

/*    @Override
    @Tool(name = "get_student_progress")
    public String getProgress(Long studentId) {

        long total = submissionRepository.countByStudentId(studentId);
        long graded = submissionRepository.countByStudentIdAndGradeNotNull(studentId);

        return "Submitted: " + total + ", Graded: " + graded;
    }*/

    @Override
    @Tool(name = "submit_assignment")
    public String submit(Long assignmentId, AssignmentSubmitRequestDto file, CohortUser user) {

        studentService.submitAssignment(assignmentId, file, user);

        return "Submission successful";
    }

    @Override
    @Tool(name = "enroll_course")
    public String enroll(Long courseId, Long batchId ,CohortUser user) {

        courseBatchService.enrollInCourse(courseId, batchId ,user);

        return "Enrolled successfully";
    }


}
