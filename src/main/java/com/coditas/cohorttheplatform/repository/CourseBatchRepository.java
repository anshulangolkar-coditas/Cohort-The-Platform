package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CourseBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseBatchRepository
        extends JpaRepository<CourseBatch, Long> {}
