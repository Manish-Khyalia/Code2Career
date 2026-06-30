package com.manish.code2career.service;

import com.manish.code2career.dto.JobRequest;
import com.manish.code2career.dto.JobResponse;
import com.manish.code2career.entity.Job;
import com.manish.code2career.exception.JobNotFoundException;
import com.manish.code2career.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public JobResponse addJob(JobRequest request) {

        Job job = Job.builder()
                .companyName(request.getCompanyName())
                .jobRole(request.getJobRole())
                .location(request.getLocation())
                .salary(request.getSalary())
                .status(request.getStatus())
                .applicationLink(request.getApplicationLink())
                .deadline(request.getDeadline())
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

        return jobRepository.findAll()
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

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"));

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

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"));

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

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"));

        jobRepository.delete(job);

        return "Job deleted successfully";
    }
}