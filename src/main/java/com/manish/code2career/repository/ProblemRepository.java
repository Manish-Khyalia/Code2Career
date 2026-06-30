package com.manish.code2career.repository;

import com.manish.code2career.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository
        extends JpaRepository<Problem, Long> {

    List<Problem> findByTopic(String topic);

    List<Problem> findByDifficulty(String difficulty);

    List<Problem> findByRevisionStatus(Boolean revisionStatus);

    long countByRevisionStatus(Boolean revisionStatus);

    long countByDifficulty(String difficulty);

}