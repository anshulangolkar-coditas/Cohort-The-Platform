package com.coditas.cohorttheplatform.config;

import com.coditas.cohorttheplatform.constants.Role;
import com.coditas.cohorttheplatform.filter.JwtFiler;
import com.coditas.cohorttheplatform.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFiler jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.csrf(AbstractHttpConfigurer::disable);

        String[] PUBLIC_URLS = {
                "/auth/**"
        };

    http.authorizeHttpRequests(
        auth ->
            auth.requestMatchers(PUBLIC_URLS)
                .permitAll()
                .requestMatchers("/courses/**")
                .hasRole(Role.ADMIN.name())
                .requestMatchers("/on-board/**")
                .hasRole(Role.ADMIN.name())
                .requestMatchers("/*/batches/**")
                .hasAnyRole(Role.ADMIN.name(), Role.INSTRUCTOR.name())
                .anyRequest()
                .permitAll());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

}
