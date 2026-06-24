package com.coditas.cohorttheplatform.service;

import com.coditas.cohorttheplatform.entity.User;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.NotFoundException;
import com.coditas.cohorttheplatform.repository.UserRepository;
import com.example.connectingyou.config.TenantContext;
import com.example.connectingyou.exception.ExceptionMessages;
import com.example.connectingyou.exception.UserNotFoundException;
import com.example.connectingyou.repository.platform.UsersRepository;
import com.example.connectingyou.repository.tenant.TenantUsersRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @NullMarked
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository
        .findByEmail(username)
        .orElseThrow(() -> new NotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }
}
