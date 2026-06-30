package com.manish.code2career.repository;

import com.manish.code2career.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    long countByStatus(String status);

}

