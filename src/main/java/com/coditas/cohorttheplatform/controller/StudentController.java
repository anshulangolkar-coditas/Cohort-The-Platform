package com.coditas.cohorttheplatform.controller;

import com.coditas.cohorttheplatform.dto.assignment.response.AssignmentResponseDto;
import com.coditas.cohorttheplatform.dto.common.CourseDetailsDto;
import com.coditas.cohorttheplatform.dto.student.request.AssignmentSubmitRequestDto;
import com.coditas.cohorttheplatform.dto.student.response.AssignmentSubmitResponseDto;
import com.coditas.cohorttheplatform.dto.student.response.CourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.service.StudentService;
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

  @GetMapping("/materials/{materialId}/download")
  public ResponseEntity<ApplicationResponse<String>> downloadMaterial(
      @PathVariable Long materialId, @AuthenticationPrincipal CohortUser user) {

    String url = studentService.downloadMaterial(materialId, user);

    return ResponseEntity.ok(
        ApplicationResponse.success(HttpStatus.OK.value(), "Download link generated", url));
  }

  @PostMapping("/assignments/{assignmentId}/submit")
  public ResponseEntity<ApplicationResponse<AssignmentSubmitResponseDto>> submitAssignment(
      @NotNull @PathVariable Long assignmentId,
      @Valid @ModelAttribute AssignmentSubmitRequestDto request,
          @AuthenticationPrincipal CohortUser student) {

    AssignmentSubmitResponseDto details = studentService.submitAssignment(assignmentId, request, student);

    return ResponseEntity.ok(ApplicationResponse.success(HttpStatus.CREATED.value(),
            "File Uploaded successfully", details));
  }
}
