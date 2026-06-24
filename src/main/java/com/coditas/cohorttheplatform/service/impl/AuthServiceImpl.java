package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.entity.User;
import com.coditas.cohorttheplatform.exception.AuthenticationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.mappings.AuthControllerMapping;
import com.coditas.cohorttheplatform.service.AuthService;
import com.coditas.cohorttheplatform.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthControllerMapping authControllerMapping;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        Authentication authentication = null;

        try {

            authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails == null) {
                throw new AuthenticationException(ExceptionMessages.AUTHENTICATION_EXCEPTION);
            }

        } catch (Exception e) {
            throw new AuthenticationException(ExceptionMessages.AUTHENTICATION_EXCEPTION);
        }

        String accessToken = jwtUtil.generateToken(((UserDetails) authentication.getPrincipal()).getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(((User) authentication.getPrincipal()));

        return authControllerMapping.loginResponse(accessToken, refreshToken);
    }
}
