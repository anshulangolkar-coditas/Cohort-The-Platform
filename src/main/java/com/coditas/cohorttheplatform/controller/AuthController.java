package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.request.RegenerateAccessTokenRequest;
import com.coditas.cohorttheplatform.dto.auth.request.RegisterRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.dto.auth.response.RegenerateAccessTokenResponse;
import com.coditas.cohorttheplatform.dto.auth.response.RegisterResponseDto;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<LoginResponseDto>> login(@Valid @RequestBody
            LoginRequestDto request){

        LoginResponseDto details = authService.login(request);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.OK.value(),
                "User Logged-in successfully", details));
    }

    @PostMapping("/instructor/register")
    public ResponseEntity<ApplicationResponse<RegisterResponseDto>> registerInstructor(@Valid @RequestBody
    RegisterRequestDto request){

        RegisterResponseDto details = authService.registerInstructor(request);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "User Registered successfully", details));
    }

    @PostMapping("/student/register")
    public ResponseEntity<ApplicationResponse<RegisterResponseDto>> registerStudent(@Valid @RequestBody
    RegisterRequestDto request){

        RegisterResponseDto details = authService.registerStudent(request);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "User Registered successfully", details));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApplicationResponse<RegenerateAccessTokenResponse>> refreshToken(@Valid @RequestBody
    RegenerateAccessTokenRequest request){

        RegenerateAccessTokenResponse details = authService.regenerateAccessToken(request);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.OK.value(),
                "Access Token generated successfully", details));
    }



}
