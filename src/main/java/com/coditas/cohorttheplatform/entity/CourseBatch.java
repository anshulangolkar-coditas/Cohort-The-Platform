package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id" ,nullable = false)
    private Course course;

    @OneToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id", nullable = false)
    private User instructor;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;

}
