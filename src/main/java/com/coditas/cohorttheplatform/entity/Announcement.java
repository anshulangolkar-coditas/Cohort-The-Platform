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
    @JoinColumn(name = "course_id", referencedColumnName = "", nullable = false)
    private Course course;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "announcement_date", nullable = false, updatable = false)
    private LocalDate announcementDate;

    @ManyToOne
    @JoinColumn(name = "announcement_made_by", referencedColumnName = "", nullable = false)
    private User announcementMadeBy;

}
