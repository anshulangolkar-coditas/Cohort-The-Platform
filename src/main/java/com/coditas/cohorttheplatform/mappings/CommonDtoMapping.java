package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.common.CohortUserDetailsDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CommonDtoMapping {

    public CohortUserDetailsDto cohortUserDetails(CohortUser user){
        return CohortUserDetailsDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .emailId(user.getEmail())
                .build();
    }

    public CourseDetailsDto courseDetails(Course course){
        return CourseDetailsDto.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .build();
    }

    public Page<CourseDetailsDto> getAllCourses(Page<Course> courses){
        return courses.map(this::courseDetails);
    }

}
