package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_material")
@Builder
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false, updatable = false, unique = true)
    private Long materialId;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @Column(name = "material_link", nullable = false)
    private String materialLink;

    @Column(name = "upload_date", nullable = false, updatable = false)
    private LocalDate uploadedOn;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id", nullable = false)
    private User instructor;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;


}
