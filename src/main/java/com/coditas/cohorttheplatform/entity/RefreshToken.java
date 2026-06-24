package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", nullable = false, updatable = false, unique = true)
    private Long refreshTokenId;

    @Column(name = "token", nullable = false, updatable = false)
    private String token;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private Date createdAt = new Date();

    @Column(name = "expires_at", nullable = false, updatable = false)
    @Builder.Default
    private Date expiresAt = new Date();

    @Column(name = "blacklisted", nullable = false)
    @Builder.Default
    private Boolean blackListed = false;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;

}
