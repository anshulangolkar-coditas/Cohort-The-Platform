package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthControllerMapping {

    public LoginResponseDto loginResponse(String accessToken, String refreshToken){
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
