package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.constants.InvitationStatus;
import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.request.RegisterRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.dto.auth.response.RegisterResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Invitation;
import com.coditas.cohorttheplatform.exception.AuthenticationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.mappings.AuthControllerMapping;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import com.coditas.cohorttheplatform.repository.InvitationRepository;
import com.coditas.cohorttheplatform.service.AuthService;
import com.coditas.cohorttheplatform.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthControllerMapping authControllerMapping;
    private final InvitationRepository invitationRepository;
    private final PasswordEncoder passwordEncoder;
    private final CohortUserRepository cohortUserRepository;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        request.setEmail(request.getEmail().trim());

        Authentication authentication = null;
        CohortUser user = null;

        try {

            authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            user = (CohortUser) authentication.getPrincipal();

            if (user == null) {
                throw new AuthenticationException(ExceptionMessages.AUTHENTICATION_EXCEPTION);
            }

        } catch (Exception e) {
            throw new AuthenticationException(ExceptionMessages.AUTHENTICATION_EXCEPTION);
        }

        String accessToken = jwtUtil.generateToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return authControllerMapping.loginResponse(accessToken, refreshToken);
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidRequestException(ExceptionMessages.PASSWORD_NOT_MATCHED);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

    Invitation invitation =
        invitationRepository
            .findByUniqueKey(request.getUniqueKey())
            .orElseThrow(() -> new InvalidRequestException(ExceptionMessages.INVALID_INVITATION));

        if (!request.getEmail().equals(invitation.getEmail())) {
            throw new InvalidRequestException(ExceptionMessages.INVALID_INVITATION);
        }

        if (invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvalidRequestException(ExceptionMessages.EXPIRED_INVITATION);
        }

        CohortUser user =
                cohortUserRepository.save(
                        authControllerMapping.registerUserToEntity(request, invitation.getRole()));

        invitation.setInvitationStatus(InvitationStatus.ACCEPTED);
        invitationRepository.save(invitation);

        return authControllerMapping.registerUserToDto(user);

    }
}
