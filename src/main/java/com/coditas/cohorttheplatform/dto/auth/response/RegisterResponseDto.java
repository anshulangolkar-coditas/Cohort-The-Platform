package com.coditas.cohorttheplatform.dto.auth.response;

import com.coditas.cohorttheplatform.constants.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {

    private Long userId;
    private String fullName;
    private String email;
    private Role role;
    private boolean activeStatus;

}
