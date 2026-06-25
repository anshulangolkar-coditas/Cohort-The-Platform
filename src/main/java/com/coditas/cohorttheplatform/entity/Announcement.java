package com.coditas.cohorttheplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announcement")
@Builder
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id", nullable = false, updatable = false, unique = true)
    private Long announcementId;

    @ManyToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "course_batch_id", nullable = false)
    private CourseBatch batch;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "announcement_date", nullable = false, updatable = false)
    private LocalDate announcementDate;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = false)
    private CohortUser createdBy;

}
