package com.coditas.cohorttheplatform.dto.auth.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegenerateAccessTokenResponse {

    private String accessToken;
    private String refreshToken;

}
