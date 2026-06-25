package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.invitation.request.InvitationRequestDto;
import com.coditas.cohorttheplatform.dto.invitation.response.InvitationResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/on-board")
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse<InvitationResponseDto>> onBoard(@Valid @RequestBody
    InvitationRequestDto request, @AuthenticationPrincipal
            CohortUser user){

        InvitationResponseDto details = invitationService.onBoard(request, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),"Invitation sent successfully", details));
    }


}
