package com.manish.code2career.service;

import com.manish.code2career.dto.DashboardResponse;
import com.manish.code2career.entity.User;
import com.manish.code2career.repository.ApplicationRepository;
import com.manish.code2career.repository.JobRepository;
import com.manish.code2career.repository.ProblemRepository;
import com.manish.code2career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobRepository jobRepository;

    private final ApplicationRepository applicationRepository;

    private final ProblemRepository problemRepository;

    private final UserRepository userRepository;

    public DashboardResponse getDashboard() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return DashboardResponse.builder()
                .totalJobs(jobRepository.countByUser(user))
                .totalApplications(applicationRepository.countByUser(user))
                .totalProblems(problemRepository.countByUser(user))
                .revisionProblems(problemRepository.countByUserAndRevisionStatus(user, true))
                .easyProblems(problemRepository.countByUserAndDifficulty(user, "Easy"))
                .mediumProblems(problemRepository.countByUserAndDifficulty(user, "Medium"))
                .hardProblems(problemRepository.countByUserAndDifficulty(user, "Hard"))
                .appliedApplications(
                        applicationRepository.countByUserAndStatus(user, "Applied")
                )
                .oaClearedApplications(
                        applicationRepository.countByUserAndStatus(user, "OA Cleared")
                )
                .interviewScheduledApplications(
                        applicationRepository.countByUserAndStatus(user, "Interview Scheduled")
                )
                .build();
    }
}