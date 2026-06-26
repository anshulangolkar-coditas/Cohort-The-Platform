package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    Page<CourseMaterial> findAllByCourse(Course course, Pageable pageable);


}
