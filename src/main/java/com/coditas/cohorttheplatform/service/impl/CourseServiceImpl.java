package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.course.request.AddCourseBatchRequestDto;
import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseBatchResponseDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.CommonDtoMapping;
import com.coditas.cohorttheplatform.mappings.CourseControllerMapping;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import com.coditas.cohorttheplatform.repository.CourseBatchRepository;
import com.coditas.cohorttheplatform.repository.CourseRepository;
import com.coditas.cohorttheplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseControllerMapping courseControllerMapping;
    private final CourseBatchRepository courseBatchRepository;
    private final CohortUserRepository cohortUserRepository;
    private final CommonDtoMapping commonDtoMapping;


    @Transactional
    @Override
    public AddCourseResponseDto addCourse(
            AddCourseRequestDto request,
            CohortUser user) {

        if(courseRepository.existsByCourseName(request.getCourseName().trim())){
            throw new InvalidRequestException(ExceptionMessages.COURSE_EXISTS);
        }

        Course course = courseRepository.save(
                courseControllerMapping.addCourseDtoToEntity(request, user));

        return courseControllerMapping.addCourseEntityToDto(course, user);

    }

    @Transactional
    @Override
    public AddCourseBatchResponseDto addCourseBatch(
            Long courseId,
            AddCourseBatchRequestDto request,
            CohortUser user) {

        if(!isBatchDateValid(request.getStartDate(), request.getEndDate())){
            throw new InvalidRequestException(ExceptionMessages.START_DATE_OR_END_DATE_INVALID);
        }
        if(courseBatchRepository.existsCourseBatchByBatchName(request.getBatchName().trim())){
            throw new InvalidRequestException(ExceptionMessages. BATCH_ALREADY_EXISTS);
        }

        Course course = findCourseById(courseId);

        if(!courseBelongsToAdmin(course, user)){
            throw new InvalidRequestException(ExceptionMessages.COURSE_USER_UNAUTHORIZED);
        }

        CohortUser instructor = findCohortUserById(request.getInstructorId());

        if(courseBatchRepository.existsCourseBatchByInstructorAndEndDateBefore(instructor, LocalDate.now())){
            throw new InvalidRequestException(ExceptionMessages.INSTRUCTOR_ALREADY_ASSIGNED);
        }

        CourseBatch batch = courseBatchRepository.save(CourseBatch.builder()
                        .batchName(request.getBatchName())
                        .course(course)
                        .enrollmentLimit(request.getEnrollmentLimit())
                        .instructor(instructor)
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .build());

        return courseControllerMapping.addCourseBatchResponse(batch, instructor, course);
    }

    @Override
    public Page<CourseDetailsDto> getAllCourses(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Course> courses = courseRepository.findAll(pageRequest);

        return commonDtoMapping.getAllCourses(courses);
    }

    private Course findCourseById(Long courseId){
        return   courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.COURSE_NOT_FOUND));
    }

    private boolean courseBelongsToAdmin(Course course, CohortUser user){
        return Objects.equals(course.getCreatedBy()
                .getUserId(), user.getUserId());
    }

    private CohortUser findCohortUserById(Long userId){
        return cohortUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }

    private boolean isBatchDateValid(LocalDate startDate, LocalDate endDate){
        return !endDate.isBefore(startDate) && !endDate.equals(startDate) && !startDate.isBefore(
                LocalDate.now()) && !endDate.isBefore(LocalDate.now());
    }

}
