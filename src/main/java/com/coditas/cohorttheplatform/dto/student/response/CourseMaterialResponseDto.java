package com.coditas.cohorttheplatform.dto.student.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseMaterialResponseDto {

    private Long materialId;
    private String fileName;
    private LocalDate uploadedAt;

}
