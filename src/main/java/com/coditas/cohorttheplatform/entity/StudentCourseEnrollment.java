package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_course_enrollment")
@Builder
public class StudentCourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id", nullable = false, updatable = false, unique = true)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false)
    private User student;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}
