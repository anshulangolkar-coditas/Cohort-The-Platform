package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponseDto login(
            @Valid LoginRequestDto request);
}
