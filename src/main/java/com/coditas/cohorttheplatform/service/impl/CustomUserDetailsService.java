package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.repository.CohortUserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final CohortUserRepository cohortUserRepository;

    @Override
    @NullMarked
    public CohortUser loadUserByUsername(String username) throws UsernameNotFoundException {

    return cohortUserRepository
        .findByEmail(username)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }
}
