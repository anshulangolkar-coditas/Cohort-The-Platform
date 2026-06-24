package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.AssignmentFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentFeedbackRepository extends JpaRepository<AssignmentFeedback, Long> {}
