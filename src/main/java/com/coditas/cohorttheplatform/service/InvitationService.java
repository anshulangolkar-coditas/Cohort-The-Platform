package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.dto.invitation.request.InvitationRequestDto;
import com.coditas.cohorttheplatform.dto.invitation.response.InvitationResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import jakarta.validation.Valid;

public interface InvitationService {
    InvitationResponseDto onBoard(
            @Valid InvitationRequestDto request,
            CohortUser user);
}
