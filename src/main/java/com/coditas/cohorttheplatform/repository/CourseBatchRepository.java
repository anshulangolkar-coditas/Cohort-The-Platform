package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CourseBatchRepository
        extends JpaRepository<CourseBatch, Long> {

    boolean existsCourseBatchByInstructorAndEndDateBefore(CohortUser instructor, LocalDate localDate);

    boolean existsCourseBatchByBatchName(String batchName);

    @Query("""
       SELECT cb
       FROM CourseBatch cb
       LEFT JOIN Enrollment e ON e.courseBatch = cb
       WHERE cb.course = :course
       AND cb.startDate > :startDate
       AND cb.isActive = true
       GROUP BY cb
       HAVING COUNT(e) < cb.enrollmentLimit
       """)
    Page<CourseBatch> findAvailableBatches(
            @Param("course") Course course,
            @Param("startDate") LocalDate startDate,
            Pageable pageable);

}
