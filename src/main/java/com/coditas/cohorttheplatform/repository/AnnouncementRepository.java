package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {}
