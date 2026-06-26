package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Assignment;
import com.coditas.cohorttheplatform.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    Page<Submission> findAllByAssignment(Assignment assignment, Pageable pageable);

}
