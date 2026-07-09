package com.manish.code2career.repository;

import com.manish.code2career.entity.Problem;
import com.manish.code2career.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository
        extends JpaRepository<Problem, Long> {

    List<Problem> findByUser(User user);

    Optional<Problem> findByIdAndUser(Long id, User user);

    List<Problem> findByUserAndTopic(User user, String topic);

    List<Problem> findByUserAndDifficulty(User user, String difficulty);

    List<Problem> findByUserAndRevisionStatus(User user, Boolean revisionStatus);

    long countByUserAndRevisionStatus(User user, Boolean revisionStatus);

    long countByUserAndDifficulty(User user, String difficulty);

    long countByUser(User user);

}