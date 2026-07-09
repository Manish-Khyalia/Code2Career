package com.manish.code2career.repository;

import com.manish.code2career.entity.Application;
import com.manish.code2career.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    long countByUserAndStatus(User user, String status);
    long countByUser(User user);

    List<Application> findByUser(User user);

    Optional<Application> findByIdAndUser(Long id, User user);

}