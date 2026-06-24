package com.example.cohorttheplatform.entity;

import com.example.cohorttheplatform.constants.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false, unique = true)
    private Long userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "joined_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate joinedDate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;



}
