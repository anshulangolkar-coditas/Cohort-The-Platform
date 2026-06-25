package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.CommonDtoMapping;
import com.coditas.cohorttheplatform.repository.CourseBatchRepository;
import com.coditas.cohorttheplatform.repository.CourseRepository;
import com.coditas.cohorttheplatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final CourseRepository courseRepository;
    private final CommonDtoMapping commonDtoMapping;
    private final CourseBatchRepository courseBatchRepository;

    @Override
    public Page<CourseDetailsDto> getAllCourses(
            int page,
            int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Course> courses = courseRepository.findAll(pageRequest);

        return commonDtoMapping.getAllCourses(courses);
    }

    @Override
    public GetAllCourseBatch getAllBatches(
            Long courseId,
            int page,
            int size) {

        Course course = findCourseById(courseId);

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CourseBatch> batches = courseBatchRepository.findAllByCourseAndStartDateAfterAndEnrollmentLimit(course,
                LocalDate.now(), pageRequest);

        return null;

    }

    private Course findCourseById(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.COURSE_NOT_FOUND));
    }

}
