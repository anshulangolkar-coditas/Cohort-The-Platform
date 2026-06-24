package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {}
