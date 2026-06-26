package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.dto.coursebatch.response.EnrollmentResponseDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseBatchControllerMapping {

    private final CommonDtoMapping commonDtoMapping;

    public AddCourseMaterialResponseDto addCourseMaterialResponse(CourseMaterial material, Course course){
        return AddCourseMaterialResponseDto.builder()
                .materialId(material.getMaterialId())
                .fileName(material.getFileName())
                .uploadedOn(material.getUploadedOn())
                .courseDetails(commonDtoMapping.courseDetails(course))
                .build();
    }


    public GetAllCourseBatch getAllCourseBatches(Course course, Page<CourseBatch> batchList){
        return GetAllCourseBatch.builder()
                .courseDetails(commonDtoMapping.courseDetails(course))
                .batchList(commonDtoMapping.getAllBatches(batchList))
                .build();

    }

    public EnrollmentResponseDto enrollmentResponse(Enrollment enrollment, Course course, CourseBatch batch,
            CohortUser student){
        return EnrollmentResponseDto.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .enrolledAt(enrollment.getEnrolledAt())
                .courseDetails(commonDtoMapping.courseDetails(course))
                .batchDetails(commonDtoMapping.batchDetails(batch))
                .studentDetails(commonDtoMapping.cohortUserDetails(student))
                .build();
    }




}
