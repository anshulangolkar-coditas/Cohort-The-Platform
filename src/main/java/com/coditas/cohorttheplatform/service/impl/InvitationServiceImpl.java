package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.dto.invitation.request.InvitationRequestDto;
import com.coditas.cohorttheplatform.dto.invitation.response.InvitationResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Invitation;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.mappings.InvitationControllerMapping;
import com.coditas.cohorttheplatform.repository.InvitationRepository;
import com.coditas.cohorttheplatform.service.EmailService;
import com.coditas.cohorttheplatform.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final EmailService emailService;
    private final InvitationControllerMapping invitationControllerMapping;

    @Transactional
    @Override
    public InvitationResponseDto onBoard(
            InvitationRequestDto request,
            CohortUser user) {


        Role invitedRole = Role.toValue(request.getRole());

        if(!invitedRole.equals(Role.INSTRUCTOR)){
            throw new InvalidRequestException(ExceptionMessages.INVALID_INVITATION_ROLE);
        }

        String uniqueKey = emailService.inviteUser(request.getEmail());

        Invitation invitation = Invitation.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .role(invitedRole)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(60))
                .uniqueKey(uniqueKey)
                .inviter(user)
                .build();

        invitationRepository.save(invitation);

        return invitationControllerMapping.invitationResponse(invitation);

    }
}
