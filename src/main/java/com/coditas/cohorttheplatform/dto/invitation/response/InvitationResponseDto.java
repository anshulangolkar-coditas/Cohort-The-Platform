package com.coditas.cohorttheplatform.dto.invitation.response;

import com.coditas.cohorttheplatform.constants.InvitationStatus;
import com.coditas.cohorttheplatform.constants.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationResponseDto {

    private Long invitationId;
    private String name;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private InvitationStatus invitationStatus;
    private Long inviterId;
    private String inviterName;

}
