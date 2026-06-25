package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.request.RegenerateAccessTokenRequest;
import com.coditas.cohorttheplatform.dto.auth.request.RegisterRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.dto.auth.response.RegenerateAccessTokenResponse;
import com.coditas.cohorttheplatform.dto.auth.response.RegisterResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponseDto login(
            @Valid LoginRequestDto request);

    RegisterResponseDto registerInstructor(
            @Valid RegisterRequestDto request);

    RegenerateAccessTokenResponse regenerateAccessToken(@Valid RegenerateAccessTokenRequest request);

    RegisterResponseDto registerStudent(
            @Valid RegisterRequestDto request);
}
