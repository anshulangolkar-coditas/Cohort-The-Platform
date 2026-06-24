package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.RepublishedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepublishedCourseRepository extends JpaRepository<RepublishedCourse, Long> {}
