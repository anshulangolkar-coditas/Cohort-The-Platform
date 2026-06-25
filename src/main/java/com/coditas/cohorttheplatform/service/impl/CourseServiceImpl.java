package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.mappings.CourseControllerMapping;
import com.coditas.cohorttheplatform.repository.CourseRepository;
import com.coditas.cohorttheplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseControllerMapping courseControllerMapping;


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
}
