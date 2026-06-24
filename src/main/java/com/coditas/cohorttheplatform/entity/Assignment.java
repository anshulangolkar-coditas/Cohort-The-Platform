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
@Table(name = "assignment")
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false, updatable = false, unique = true)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @Column(name = "uploaded_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime uploadedDateTime;

    @Column(name = "deadline_date", nullable = false)
    private LocalDateTime deadlineDateAndTime;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_id", nullable = false)
    private User uploadedBy;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean isActive = true;


}
