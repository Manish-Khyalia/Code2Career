package com.manish.code2career.service;

import com.manish.code2career.dto.DashboardResponse;
import com.manish.code2career.repository.ApplicationRepository;
import com.manish.code2career.repository.JobRepository;
import com.manish.code2career.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobRepository jobRepository;

    private final ApplicationRepository applicationRepository;

    private final ProblemRepository problemRepository;

    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalJobs(jobRepository.count())
                .totalApplications(applicationRepository.count())
                .totalProblems(problemRepository.count())
                .revisionProblems(problemRepository.countByRevisionStatus(true))
                .easyProblems(problemRepository.countByDifficulty("Easy"))
                .mediumProblems(problemRepository.countByDifficulty("Medium"))
                .hardProblems(problemRepository.countByDifficulty("Hard"))
                .appliedApplications(
                        applicationRepository.countByStatus("Applied")
                )
                .oaClearedApplications(
                        applicationRepository.countByStatus("OA Cleared")
                )
                .interviewScheduledApplications(
                        applicationRepository.countByStatus("Interview Scheduled")
                )
                .build();
    }
}