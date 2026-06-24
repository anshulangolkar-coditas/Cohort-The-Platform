package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long> {}
