package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.EnrollmentResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.SubmissionResponseDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.entity.*;
import com.coditas.cohorttheplatform.exception.AuthorizationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.CourseBatchControllerMapping;
import com.coditas.cohorttheplatform.repository.*;
import com.coditas.cohorttheplatform.service.CourseBatchService;
import com.coditas.cohorttheplatform.service.EmailService;
import com.coditas.cohorttheplatform.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseBatchServiceImpl implements CourseBatchService {

  private final CourseMaterialRepository courseMaterialRepository;
  private final CourseRepository courseRepository;
  private final CourseBatchRepository courseBatchRepository;
  private final S3Service s3Service;
  private final CourseBatchControllerMapping courseBatchControllerMapping;
  private final EmailService emailService;
  private final CohortUserRepository cohortUserRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final AssignmentRepository assignmentRepository;
  private final SubmissionRepository submissionRepository;

  @Transactional
  @Override
  public AddCourseMaterialResponseDto addMaterial(
      Long courseId, Long batchId, AddCourseMaterialRequestDto request, CohortUser user) {

    Course course = findCourseById(courseId);
    CourseBatch batch = findCourseBatchById(batchId);

    if (!batch.getCourse().getCourseId().equals(course.getCourseId())) {
      throw new AuthorizationException(ExceptionMessages.BATCH_COURSE_MISMATCH);
    }

    if(!batch.getInstructor().getUserId().equals(user.getUserId()) && !user.getRole().equals(Role.ADMIN)){
      throw new InvalidRequestException(ExceptionMessages.NOT_ALLOWED);
    }

    String fileKey = s3Service.uploadFile(request.getFile());

    CourseMaterial material =
        courseMaterialRepository.save(
            CourseMaterial.builder()
                .course(course)
                .fileName(request.getFile().getOriginalFilename())
                .fileKey(fileKey)
                .instructor(user)
                .build());

    List<String> emails = cohortUserRepository.getAllEmailIdByCourseId(course.getCourseId());

    try {
      emailService.materialUploadEmail(emails);
    } catch (Exception ex) {
      log.error("Email Sending failed", ex);
    }

    return courseBatchControllerMapping.addCourseMaterialResponse(material, course);
  }

  @Override
  public GetAllCourseBatch getAllBatches(
          Long courseId,
          int page,
          int size) {

    Course course = findCourseById(courseId);

    PageRequest pageRequest = PageRequest.of(page, size);

    Page<CourseBatch> batches = courseBatchRepository.findAvailableBatches(course,
            LocalDate.now(), pageRequest);

    return courseBatchControllerMapping.getAllCourseBatches(course, batches);

  }

  @Override
  public EnrollmentResponseDto enrollInCourse(
          Long courseId,
          Long batchId,
          CohortUser user) {

    Course course = findCourseById(courseId);
    CourseBatch courseBatch = findCourseBatchById(batchId);

    courseEnrollmentValidation(course, courseBatch, user);

    Enrollment enrollment = enrollmentRepository.save(Enrollment.builder()
            .courseBatch(courseBatch)
            .student(user)
            .build());

    return courseBatchControllerMapping.enrollmentResponse(enrollment, course, courseBatch, user);
  }

    @Override
    public Page<SubmissionResponseDto> getAllSubmissions(
            Long batchId,
            Long assignmentId,
            CohortUser user,int page, int size) {

    CourseBatch batch = findCourseBatchById(batchId);
    Assignment assignment = findAssigmentById(assignmentId);

    PageRequest pageRequest = PageRequest.of(page, size);

    if(!assignment.getCourseBatch().getCourseBatchId().equals(batch.getCourseBatchId())){
      throw new AuthorizationException(ExceptionMessages.BATCH_ASSIGNMENT_MISMATCH);
    }

    Page<Submission> submissions = submissionRepository.findAllByAssignment(assignment, pageRequest);

    return courseBatchControllerMapping.getAllSubmissions(submissions);

    }


    private Course findCourseById(Long courseId) {
    return courseRepository
        .findById(courseId)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.COURSE_NOT_FOUND));
  }

  private CourseBatch findCourseBatchById(Long batchId) {
    return courseBatchRepository
        .findById(batchId)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.BATCH_NOT_FOUND));
  }

  private Assignment findAssigmentById(Long assignmentId){
    return assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new NotFoundException(ExceptionMessages.ASSIGNMENT_NOT_FOUND));
  }

  private void courseEnrollmentValidation(Course course, CourseBatch batch, CohortUser user){

    if(!batch.getCourse().getCourseId().equals(course.getCourseId())){
      throw new InvalidRequestException(ExceptionMessages.BATCH_COURSE_MISMATCH);
    }

    if(!course.isActive() || !batch.isActive()){
      throw new InvalidRequestException(ExceptionMessages.COURSE_INACTIVE);
    }

    if(!batch.getStartDate().isAfter(LocalDate.now())){
      throw new InvalidRequestException(ExceptionMessages.BATCH_ALREADY_STARTED);
    }

    if(enrollmentRepository.existsByCourseBatchAndStudent(batch, user)){
      throw new InvalidRequestException(ExceptionMessages.ALREADY_ENROLLED);
    }
  }

}
