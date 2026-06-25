package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.course.request.AddCourseBatchRequestDto;
import com.coditas.cohorttheplatform.dto.course.request.AddCourseRequestDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseBatchResponseDto;
import com.coditas.cohorttheplatform.dto.course.response.AddCourseResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


    @PostMapping
    public ResponseEntity<ApplicationResponse<AddCourseResponseDto>> addCourse(@Valid @RequestBody
    AddCourseRequestDto request, @AuthenticationPrincipal
            CohortUser user){

        AddCourseResponseDto details = courseService.addCourse(request, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "Course Added Successfully", details));
    }


    @GetMapping
    public ResponseEntity<ApplicationResponse<Page<CourseDetailsDto>>> getAllCourses( @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "8") int size){

        Page<CourseDetailsDto> courseList = courseService.getAllCourses(page, size);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.OK.value(),
                "Fetched all courses successfully", courseList));
    }


    @PostMapping("/{courseId}/batch")
    public ResponseEntity<ApplicationResponse<AddCourseBatchResponseDto>> addCourseBatch(
            @NotNull
            @PathVariable
            Long courseId,
            @Valid @RequestBody
            AddCourseBatchRequestDto request,
            @AuthenticationPrincipal CohortUser user){

        AddCourseBatchResponseDto details = courseService.addCourseBatch(courseId, request, user);

        return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
                "Batch created successfully",details));
    }



}
