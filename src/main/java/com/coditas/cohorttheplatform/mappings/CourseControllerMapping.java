package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseBatchResponseDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseControllerMapping {

    private final CommonDtoMapping commonDtoMapping;

    public Course addCourseDtoToEntity(AddCourseRequestDto request, CohortUser user){
        return Course.builder()
                .courseName(request.getCourseName())
                .courseDescription(request.getCourseDescription())
                .createdBy(user)
                .build();
    }

    public AddCourseResponseDto addCourseEntityToDto(Course course, CohortUser user){
        return AddCourseResponseDto.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .createdBy(commonDtoMapping.cohortUserDetails(user))
                .build();
    }

    public AddCourseBatchResponseDto addCourseBatchResponse(CourseBatch batch, CohortUser instructor, Course course){
        return AddCourseBatchResponseDto.builder()
                .batchId(batch.getCourseBatchId())
                .batchName(batch.getBatchName())
                .startDate(batch.getStartDate())
                .endDate(batch.getEndDate())
                .createdAt(batch.getCreatedAt())
                .instructorDetails(commonDtoMapping.cohortUserDetails(instructor))
                .courseDetails(commonDtoMapping.courseDetails(course))
                .build();
    }


}
