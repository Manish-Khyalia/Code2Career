package com.manish.code2career.repository;

import com.manish.code2career.entity.Job;
import com.manish.code2career.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByUser(User user);
    Optional<Job> findByIdAndUser(Long id, User user);
    long countByUser(User user);

}