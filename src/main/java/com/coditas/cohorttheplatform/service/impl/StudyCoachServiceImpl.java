package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.dto.studycoach.request.StudyCoachRequest;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.repository.AssignmentRepository;
import com.coditas.cohorttheplatform.repository.EnrollmentRepository;
import com.coditas.cohorttheplatform.repository.SubmissionRepository;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.StudyCoachService;
import com.coditas.cohorttheplatform.service.StudyTools;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class StudyCoachServiceImpl implements StudyCoachService {

  private final ChatClient chatClient;
  private final StudyTools tools;

  @Override
  public String chat(
          String message,
          CohortUser user) {

    String systemPrompt = """
                You are Study Coach for an LMS system.
                You can:
                1. check assignments due
                2. check grades
                3. submit assignments
                4. enroll in courses

                Alw ays use user context and never access other users data.
                """;

    return chatClient.prompt()
            .system(systemPrompt)
            .user(message)
            .tools(tools)
            .call()
            .content();

  }
}
