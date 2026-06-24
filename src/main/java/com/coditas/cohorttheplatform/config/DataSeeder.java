package com.coditas.cohorttheplatform.config;

import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.entity.User;
import com.coditas.cohorttheplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(User.builder()
                .fullName("Anshul Angolkar")
                .email("anshul.angolkar@coditas.com")
                .role(Role.ADMIN)
                .joinedDate(LocalDate.parse("2026-06-24"))
                .password(passwordEncoder.encode("anshul@123"))
                .build());

    }
}
