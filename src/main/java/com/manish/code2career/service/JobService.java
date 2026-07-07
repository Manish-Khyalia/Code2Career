package com.manish.code2career.service;

import com.manish.code2career.dto.JobRequest;
import com.manish.code2career.dto.JobResponse;
import com.manish.code2career.entity.Job;
import com.manish.code2career.exception.JobNotFoundException;
import com.manish.code2career.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.manish.code2career.entity.User;
import com.manish.code2career.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobResponse addJob(JobRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = Job.builder()
                .companyName(request.getCompanyName())
                .jobRole(request.getJobRole())
                .location(request.getLocation())
                .salary(request.getSalary())
                .status(request.getStatus())
                .applicationLink(request.getApplicationLink())
                .deadline(request.getDeadline())
                .user(user)
                .build();

        Job savedJob = jobRepository.save(job);

        return JobResponse.builder()
                .id(savedJob.getId())
                .companyName(savedJob.getCompanyName())
                .jobRole(savedJob.getJobRole())
                .location(savedJob.getLocation())
                .salary(savedJob.getSalary())
                .status(savedJob.getStatus())
                .applicationLink(savedJob.getApplicationLink())
                .deadline(savedJob.getDeadline())
                .build();
    }

    public List<JobResponse> getAllJobs() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return jobRepository.findByUser(user)
                .stream()
                .map(job -> JobResponse.builder()
                        .id(job.getId())
                        .companyName(job.getCompanyName())
                        .jobRole(job.getJobRole())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .status(job.getStatus())
                        .applicationLink(job.getApplicationLink())
                        .deadline(job.getDeadline())
                        .build())
                .toList();
    }

    public JobResponse getJobById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found"));

        return JobResponse.builder()
                .id(job.getId())
                .companyName(job.getCompanyName())
                .jobRole(job.getJobRole())
                .location(job.getLocation())
                .salary(job.getSalary())
                .status(job.getStatus())
                .applicationLink(job.getApplicationLink())
                .deadline(job.getDeadline())
                .build();
    }

    public JobResponse updateJob(
            Long id,
            JobRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found"));

        job.setCompanyName(request.getCompanyName());
        job.setJobRole(request.getJobRole());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setStatus(request.getStatus());
        job.setApplicationLink(request.getApplicationLink());
        job.setDeadline(request.getDeadline());

        Job updatedJob = jobRepository.save(job);

        return JobResponse.builder()
                .id(updatedJob.getId())
                .companyName(updatedJob.getCompanyName())
                .jobRole(updatedJob.getJobRole())
                .location(updatedJob.getLocation())
                .salary(updatedJob.getSalary())
                .status(updatedJob.getStatus())
                .applicationLink(updatedJob.getApplicationLink())
                .deadline(updatedJob.getDeadline())
                .build();
    }

    public String deleteJob(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found"));

        jobRepository.delete(job);

        return "Job deleted successfully";
    }
}