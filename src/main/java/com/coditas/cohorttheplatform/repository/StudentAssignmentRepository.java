package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {}
