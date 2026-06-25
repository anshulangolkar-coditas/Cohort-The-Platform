package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignment")
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false, updatable = false, unique = true)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "course_batch_id", referencedColumnName = "course_batch_id", nullable = false)
    private CourseBatch courseBatch;

    @Column(name = "assignment_title", nullable = false)
    private String assignmentTitle;

    @Column(name = "assignment_description", nullable = false)
    private String assignmentDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "deadline_date", nullable = false)
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_id", nullable = false)
    private CohortUser uploadedBy;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean isActive = true;

}
