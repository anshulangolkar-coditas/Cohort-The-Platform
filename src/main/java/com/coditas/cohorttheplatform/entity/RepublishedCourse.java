package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "republished_course")
@Builder
public class RepublishedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "republised_course_id", nullable = false, updatable = false, unique = true)
    private Long republishedCourseId;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id" ,nullable = false)
    private Course course;

    @Column(name = "enrollment_limit", nullable = false)
    private Long enrollmentLimit;

    @OneToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id", nullable = false)
    private User instructor;

    @Column(name = "course_link", nullable = false, unique = true)
    private String courseLink;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;

}
