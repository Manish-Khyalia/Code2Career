package com.manish.code2career.repository;

import com.manish.code2career.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository
        extends JpaRepository<Resume, Long> {

}