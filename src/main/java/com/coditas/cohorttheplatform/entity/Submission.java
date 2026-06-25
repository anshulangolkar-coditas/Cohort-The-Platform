package com.coditas.cohorttheplatform.entity;

import com.coditas.cohorttheplatform.constants.AssignmentStatus;
import com.coditas.cohorttheplatform.constants.Grade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submission")
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id", nullable = false, updatable = false, unique = true)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id",referencedColumnName = "assignment_id", nullable = false, updatable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false, updatable = false)
    private CohortUser student;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_key", nullable = false)
    private String fileKey;

    @Column(name = "submitted_at", nullable = false, updatable = false, unique = true)
    @CreationTimestamp
    private LocalDateTime submittedAt;

    @Column(name = "submission_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AssignmentStatus submissionStatus = AssignmentStatus.NOT_SUBMITTED;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "graded_by", referencedColumnName = "user_id")
    private CohortUser instructor;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

}
