package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollment")
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id", nullable = false, updatable = false, unique = true)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "batch_id",referencedColumnName = "course_batch_id", nullable = false)
    private CourseBatch courseBatch;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false)
    private CohortUser student;

    @Column(name = "enrolled_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate enrolledAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}
