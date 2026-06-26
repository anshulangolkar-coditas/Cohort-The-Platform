package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.StudentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

  @GetMapping("/courses")
  public ResponseEntity<ApplicationResponse<Page<CourseDetailsDto>>> getAllCourses(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "8") int size) {

      Page<CourseDetailsDto> courses = studentService.getAllCourses(page, size);

      return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.OK.value(), "Fetched all the courses " +
              "successfully", courses));
  }




}
