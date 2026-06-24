package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


/*    @PostMapping
    public ResponseEntity<ApplicationResponse<>>*/


}
