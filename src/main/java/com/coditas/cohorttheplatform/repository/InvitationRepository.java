package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByUniqueKey(String uniqueKey);
}
