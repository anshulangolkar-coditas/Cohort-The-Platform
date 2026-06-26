package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.GetAllCourseBatch;
import com.coditas.cohorttheplatform.dto.student.response.CourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.StudentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    return ResponseEntity.ok(
        ApplicationResponse.success(
            HttpStatus.OK.value(), "Fetched all the courses " + "successfully", courses));
  }

  @GetMapping("/batches/{batchId}/assignments")
  public ResponseEntity<ApplicationResponse<Page<AssignmentResponseDto>>> getAssignedAssignments(
      @NotNull @PathVariable Long batchId,
      @AuthenticationPrincipal CohortUser student,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "8") int size) {

    Page<AssignmentResponseDto> details =
        studentService.getAssignedAssignments(batchId, student, page, size);

    return ResponseEntity.ok(
        ApplicationResponse.success(
            HttpStatus.OK.value(), "Retrieved All Assignments Successfully", details));
  }

  @GetMapping("/{courseId}/materials")
  public ResponseEntity<ApplicationResponse<Page<CourseMaterialResponseDto>>> getAllMaterials(
      @PathVariable Long courseId,
      @AuthenticationPrincipal CohortUser user,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "8") int size) {

    Page<CourseMaterialResponseDto> materials =
        studentService.getAllMaterials(courseId, user, page, size);

    return ResponseEntity.ok(
        ApplicationResponse.success(
            HttpStatus.OK.value(), "Materials fetched successfully", materials));
  }
}
