package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {}
