package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.dto.auth.request.RegisterRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.dto.auth.response.RegisterResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import org.springframework.stereotype.Component;

@Component
public class AuthControllerMapping {

    public LoginResponseDto loginResponse(String accessToken, String refreshToken){
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public CohortUser registerUserToEntity(RegisterRequestDto request, Role role){
        return CohortUser.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .role(role)
                .password(request.getPassword())
                .build();
    }

    public RegisterResponseDto registerUserToDto(CohortUser user){
        return RegisterResponseDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .activeStatus(user.isActive())
                .build();
    }

}
