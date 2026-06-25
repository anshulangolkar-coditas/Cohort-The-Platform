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
@Table(name = "course_batch")
@Builder
public class CourseBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_batch_id", nullable = false, updatable = false, unique = true)
    private Long courseBatchId;

    @Column(name = "batch_name", nullable = false)
    private String batchName;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id" ,nullable = false)
    private Course course;

    @Column(name = "enrollment_limit", nullable = false)
    private Long enrollmentLimit;

    @OneToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id", nullable = false)
    private CohortUser instructor;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDate endDate;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

}
