package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, updatable = false, unique = true)
    private Long courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "enrollment_limit", nullable = false)
    private Long enrollmentLimit;

    @Column(name = "course_link", nullable = false, unique = true)
    private String courseLink;

    @Column(name = "is-active", nullable = false)
    @Builder.Default
    private boolean isActive = true;

    @OneToMany(mappedBy = "course")
    private List<CourseBatch> courseBatches;

}
