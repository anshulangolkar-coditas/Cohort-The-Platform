package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.Course;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CourseBatchRepository
        extends JpaRepository<CourseBatch, Long> {

    boolean existsCourseBatchByInstructorAndEndDateBefore(CohortUser instructor, LocalDate localDate);

    boolean existsCourseBatchByBatchName(String batchName);

    Page<CourseBatch> findAllByCourseAndStartDateAfterAndEnrollmentLimit(
            Course course,
            LocalDate startDateAfter,
            Long enrollmentLimit, Pageable pageable);


}
