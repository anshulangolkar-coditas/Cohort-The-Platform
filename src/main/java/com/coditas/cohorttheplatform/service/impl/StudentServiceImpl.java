package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.entity.Course;
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

    private Course findCourseById(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.COURSE_NOT_FOUND));
    }

}
