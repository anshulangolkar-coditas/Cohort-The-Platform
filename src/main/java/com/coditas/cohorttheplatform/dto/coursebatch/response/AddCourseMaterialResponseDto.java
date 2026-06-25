package com.coditas.cohorttheplatform.dto.coursebatch.response;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseMaterialResponseDto {

    private Long materialId;
    private String materialName;
    private String fileName;
    private LocalDate uploadedOn;
    private CourseDetailsDto courseDetails;

}
