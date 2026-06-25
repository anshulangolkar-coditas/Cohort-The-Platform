package com.coditas.cohorttheplatform.repository;

import com.coditas.cohorttheplatform.entity.CohortUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CohortUserRepository
        extends JpaRepository<CohortUser, Long> {
    Optional<CohortUser> findByEmail(String username);

    boolean existsByEmail(String email);

    @Query(
            value =
                    """
                          SELECT s.email
                          FROM Enrollment e
                          JOIN e.student s
                          WHERE e.courseBatch.course.courseId = :courseId
                      """)
    List<String> getAllEmailIdByCourseId(Long courseId);


}
