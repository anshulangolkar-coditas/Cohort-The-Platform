package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.request.AssignmentSubmitRequestDto;
import com.coditas.cohorttheplatform.dto.student.response.AssignmentSubmitResponseDto;
import com.coditas.cohorttheplatform.dto.student.response.CourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.*;
import com.coditas.cohorttheplatform.exception.*;
import com.coditas.cohorttheplatform.mappings.AssignmentControllerMapping;
import com.coditas.cohorttheplatform.mappings.CommonDtoMapping;
import com.coditas.cohorttheplatform.mappings.StudentControllerMapping;
import com.coditas.cohorttheplatform.repository.*;
import com.coditas.cohorttheplatform.service.S3Service;
import com.coditas.cohorttheplatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final CourseRepository courseRepository;
  private final CommonDtoMapping commonDtoMapping;
  private final CourseBatchRepository courseBatchRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final AssignmentRepository assignmentRepository;
  private final AssignmentControllerMapping assignmentControllerMapping;
  private final CourseMaterialRepository courseMaterialRepository;
  private final StudentControllerMapping studentControllerMapping;
  private final S3Service s3Service;
  private final SubmissionRepository submissionRepository;

  @Override
  public Page<CourseDetailsDto> getAllCourses(int page, int size) {

    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Course> courses = courseRepository.findAll(pageRequest);

    return commonDtoMapping.getAllCourses(courses);
  }

  @Override
  public Page<AssignmentResponseDto> getAssignedAssignments(
      Long batchId, CohortUser student, int page, int size) {

    CourseBatch batch = findBatchById(batchId);

    if (!enrollmentRepository.existsByCourseBatchAndStudent(batch, student)) {
      throw new InvalidRequestException(ExceptionMessages.NOT_ENROLLED);
    }

    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Assignment> assignments = assignmentRepository.findAllByCourseBatch(batch, pageRequest);

    return assignmentControllerMapping.getAllAssignments(assignments);
  }

  @Override
  public Page<CourseMaterialResponseDto> getAllMaterials(
          Long courseId,
          CohortUser user,
          int page,
          int size) {

    PageRequest pageRequest = PageRequest.of(page, size);

    Course course = findCourseById(courseId);

    boolean enrolled = enrollmentRepository.existsByStudentAndCourseBatchCourse(user, course);

    if (!enrolled) {
      throw new AuthorizationException(ExceptionMessages.NOT_ENROLLED);
    }

    Page<CourseMaterial> material = courseMaterialRepository.findAllByCourse(course, pageRequest);

    return studentControllerMapping.getAllMaterials(material);

  }

  @Override
  public String downloadMaterial(
          Long materialId,
          CohortUser user) {

    CourseMaterial material = findMaterialById(materialId);

    boolean enrolled = enrollmentRepository
            .existsByStudentAndCourseBatchCourse(user, material.getCourse());

    if (!enrolled) {
      throw new AuthorizationException(ExceptionMessages.NOT_ENROLLED);
    }

    return s3Service.generatePresignedUrl(material.getFileKey());
  }

  @Override
  public AssignmentSubmitResponseDto submitAssignment(
          Long assignmentId,
          AssignmentSubmitRequestDto request, CohortUser  student) {

    Assignment assignment = findAssignmentById(assignmentId);

    if(!enrollmentRepository.existsByCourseBatchAndStudent(assignment.getCourseBatch(), student)){
      throw new AuthorizationException(ExceptionMessages.NOT_ENROLLED);
    }

    if (LocalDateTime.now().isAfter(assignment.getDeadline())) {
      throw new InvalidRequestException(ExceptionMessages.DEADLINE_PASSED);
    }

    String fileKey = s3Service.uploadFile(request.getFile());

    Submission submission = submissionRepository.save(Submission.builder()
            .assignment(assignment)
            .student(student)
            .fileName(request.getFile().getOriginalFilename())
            .fileKey(fileKey)
            .submittedAt(LocalDateTime.now())
            .build());

    return studentControllerMapping.assignmentSubmitResponse(submission);
  }

  private Course findCourseById(Long courseId) {
    return courseRepository
        .findByCourseIdAndIsActive(courseId, true)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.COURSE_NOT_FOUND));
  }

  private CourseBatch findBatchById(Long batchId) {
    return courseBatchRepository
        .findById(batchId)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.BATCH_NOT_FOUND));
  }

  private CourseMaterial findMaterialById(Long materialId){
    return courseMaterialRepository.findById(materialId)
            .orElseThrow(() -> new NotFoundException(ExceptionMessages.MATERIAL_NOT_FOUND));
  }

  private Assignment findAssignmentById(Long assignmentId){
    return assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new NotFoundException(ExceptionMessages.ASSIGNMENT_NOT_FOUND));
  }

}
