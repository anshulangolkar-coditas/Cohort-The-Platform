package com.coditas.cohorttheplatform.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CohortUserDetails {

    private Long userId;
    private String fullName;
    private String emailId;

}
