package com.coditas.cohorttheplatform.dto.student;

import com.coditas.cohorttheplatform.dto.common.BatchDetailsDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import org.springframework.data.domain.Page;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCourseBatch {

    private CourseDetailsDto courseDetails;
    private Page<BatchDetailsDto> batchList;

}
