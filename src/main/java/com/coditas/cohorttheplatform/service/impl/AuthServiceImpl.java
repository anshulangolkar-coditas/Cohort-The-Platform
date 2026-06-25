package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.constants.InvitationStatus;
import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.dto.auth.request.LoginRequestDto;
import com.coditas.cohorttheplatform.dto.auth.request.RegenerateAccessTokenRequest;
import com.coditas.cohorttheplatform.dto.auth.request.RegisterRequestDto;
import com.coditas.cohorttheplatform.dto.auth.response.LoginResponseDto;
import com.coditas.cohorttheplatform.dto.auth.response.RegenerateAccessTokenResponse;
import com.coditas.cohorttheplatform.dto.auth.response.RegisterResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Invitation;
import com.coditas.cohorttheplatform.entity.RefreshToken;
import com.coditas.cohorttheplatform.exception.AuthenticationException;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.mappings.AuthControllerMapping;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import com.coditas.cohorttheplatform.repository.InvitationRepository;
import com.coditas.cohorttheplatform.repository.RefreshTokenRepository;
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
  private final RefreshTokenRepository refreshTokenRepository;

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
  public RegisterResponseDto registerInstructor(RegisterRequestDto request) {

      if(userExists(request.getEmail())){
          throw new InvalidRequestException(ExceptionMessages.USER_ALREADY_EXISTS);
      }

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

  @Override
  public RegenerateAccessTokenResponse regenerateAccessToken(RegenerateAccessTokenRequest request) {

    RefreshToken refreshToken =
        refreshTokenRepository
            .findRefreshTokenByToken(request.getRefreshToken())
            .orElseThrow(() -> new NotFoundException(ExceptionMessages.REFRESH_TOKEN_NOT_FOUND));

    if (jwtUtil.isRefreshTokenValid(refreshToken)) {
      CohortUser user = refreshToken.getCohortUser();
      String accessToken = jwtUtil.generateToken(user.getUsername());

      return RegenerateAccessTokenResponse.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken.getToken())
          .build();
    }
    throw new NotFoundException(ExceptionMessages.REFRESH_TOKEN_EXPIRED);
  }

    @Override
    public RegisterResponseDto registerStudent(RegisterRequestDto request) {

        if(userExists(request.getEmail())){
            throw new InvalidRequestException(ExceptionMessages.USER_ALREADY_EXISTS);
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidRequestException(ExceptionMessages.PASSWORD_NOT_MATCHED);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        CohortUser user =
                cohortUserRepository.save(
                        authControllerMapping.registerUserToEntity(request, Role.STUDENT));

        return authControllerMapping.registerUserToDto(user);

    }

    private boolean userExists(String email){
      return cohortUserRepository.existsByEmail(email);
    }

}
