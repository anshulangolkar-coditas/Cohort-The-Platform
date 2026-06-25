package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.invitation.response.InvitationResponseDto;
import com.coditas.cohorttheplatform.entity.Invitation;
import org.springframework.stereotype.Component;

@Component
public class InvitationControllerMapping {

    public InvitationResponseDto invitationResponse(Invitation invitation){
        return InvitationResponseDto.builder()
                .invitationId(invitation.getInvitationId())
                .name(invitation.getFullName())
                .role(invitation.getRole())
                .createdAt(invitation.getCreatedAt())
                .expiresAt(invitation.getExpiresAt())
                .invitationStatus(invitation.getInvitationStatus())
                .inviterId(invitation.getInviter().getUserId())
                .inviterName(invitation.getInviter().getFullName())
                .build();
    }

}
