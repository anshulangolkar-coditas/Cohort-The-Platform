package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.coursebatch.response.AddCourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseMaterial;
import lombok.RequiredArgsConstructor;
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


}
