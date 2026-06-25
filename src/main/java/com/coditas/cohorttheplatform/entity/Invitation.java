package com.coditas.cohorttheplatform.entity;

import com.coditas.cohorttheplatform.constants.InvitationStatus;
import com.coditas.cohorttheplatform.constants.Role;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "invitation")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id",unique = true, nullable = false, updatable = false)
    private Long invitationId;

    @Column(name = "full_name", nullable = false, updatable = false)
    private String fullName;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false, updatable = false)
    private LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30L);

    @Column(name = "unique_key", nullable = false, updatable = false, unique = true)
    private String uniqueKey;

    @Column(name = "invitation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InvitationStatus invitationStatus = InvitationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "inviter_id", referencedColumnName = "user_id")
    private CohortUser inviterId;

}
