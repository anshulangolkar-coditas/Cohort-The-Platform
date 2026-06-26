package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.CourseBatch;
import com.coditas.cohorttheplatform.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    boolean existsByCourseBatchAndStudent(
            CourseBatch courseBatch,
            CohortUser student);
    
    
}
