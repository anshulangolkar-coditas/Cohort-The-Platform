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
@Table(name = "student_assignment")
@Builder
public class StudentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_assignment_id", nullable = false, updatable = false, unique = true)
    private Long studentAssignmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, updatable = false)
    private Assignment student;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false, updatable = false)
    private Assignment assignment;

    @Column(name = "assignment_upload_link", nullable = false)
    private String uploadLink;

    @Column(name = "upload_date", nullable = false, updatable = false, unique = true)
    @CreationTimestamp
    private LocalDateTime uploadDateAndTime;


}
