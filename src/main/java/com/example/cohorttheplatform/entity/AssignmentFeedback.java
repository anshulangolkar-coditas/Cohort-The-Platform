package com.example.cohorttheplatform.entity;

import com.example.cohorttheplatform.constants.Grades;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignment_feedback")
@Builder
public class AssignmentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false, updatable = false, unique = true)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignments assignment;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Assignments instructor;

    @Column(name = "feedback", nullable = false)
    private String feedback;

    @Column(name = "course_id", nullable = false, updatable = false)
    private Grades grade;


}
