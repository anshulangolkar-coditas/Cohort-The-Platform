package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.studycoach.request.StudyCoachRequest;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.StudyCoachService;
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
@RequestMapping("/study-coach")
public class StudyCoachController {

    private final StudyCoachService studyCoachService;

    @PostMapping("/chat")
    public ResponseEntity<ApplicationResponse<String>> chat(
            @Valid
            @RequestBody
            StudyCoachRequest request, @AuthenticationPrincipal
            CohortUser user) {

        String response = studyCoachService.chat(request.getMessage(), user);

        return ResponseEntity.ok(
                ApplicationResponse.success(HttpStatus.OK.value(), "Coach response generated", response));
    }

}
