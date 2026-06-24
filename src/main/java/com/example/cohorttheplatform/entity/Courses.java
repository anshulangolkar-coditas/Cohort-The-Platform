package com.example.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
@Builder
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, updatable = false, unique = true)
    private Long courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "upload_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate uploadedDate;

    @Column(name = "enrollment_limit", nullable = false)
    private Long enrollmentLimit;

    @OneToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id", nullable = false)
    private Users instructor;

    @Column(name = "course_link", nullable = false, unique = true)
    private String courseLink;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;



}
