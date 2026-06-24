package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
