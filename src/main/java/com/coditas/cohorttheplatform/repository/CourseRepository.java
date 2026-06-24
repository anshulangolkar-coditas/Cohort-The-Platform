package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {}
