package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCourseName(String courseName);

    Page<Course> findAllCourseByCreatedBy(
            CohortUser createdBy,
            Pageable pageable);


}
