package com.coditas.cohorttheplatform.dto.common;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchDetailsDto {

    private Long batchId;
    private String batchName;
    private LocalDate startDate;
    private LocalDate endDate;

}
