package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Page<Assignment> findAllByCourseBatch(
            CourseBatch courseBatch,
            Pageable pageable);

}
