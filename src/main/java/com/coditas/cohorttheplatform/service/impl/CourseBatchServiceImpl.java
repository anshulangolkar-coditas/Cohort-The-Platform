package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.coursebatch.request.AddCourseMaterialRequestDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.entity.CourseMaterial;
import com.coditas.cohorttheplatform.exception.AuthorizationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.CourseBatchControllerMapping;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import com.coditas.cohorttheplatform.repository.CourseBatchRepository;
import com.coditas.cohorttheplatform.repository.CourseMaterialRepository;
import com.coditas.cohorttheplatform.repository.CourseRepository;
import com.coditas.cohorttheplatform.service.CourseBatchService;
import com.coditas.cohorttheplatform.service.EmailService;
import com.coditas.cohorttheplatform.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseBatchServiceImpl implements CourseBatchService {

  private final CourseMaterialRepository courseMaterialRepository;
  private final CourseRepository courseRepository;
  private final CourseBatchRepository courseBatchRepository;
  private final S3Service s3Service;
  private final CourseBatchControllerMapping courseBatchControllerMapping;
  private final EmailService emailService;
  private final CohortUserRepository cohortUserRepository;

  @Transactional
  @Override
  public AddCourseMaterialResponseDto addMaterial(
      Long courseId, Long batchId, AddCourseMaterialRequestDto request, CohortUser user) {

    Course course = findCourseById(courseId);
    CourseBatch batch = findCourseBatchById(batchId);

    if (!batch.getCourse().getCourseId().equals(course.getCourseId())) {
      throw new AuthorizationException(ExceptionMessages.BATCH_COURSE_MISMATCH);
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

    emailService.materialUploadEmail(emails);

    return courseBatchControllerMapping.addCourseMaterialResponse(material, course);
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
}
