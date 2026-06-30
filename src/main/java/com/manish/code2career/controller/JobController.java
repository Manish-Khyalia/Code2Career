package com.manish.code2career.controller;

import com.manish.code2career.dto.JobRequest;
import com.manish.code2career.dto.JobResponse;
import com.manish.code2career.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public JobResponse addJob(
            @Valid @RequestBody JobRequest request) {

        return jobService.addJob(request);
    }

    @GetMapping
    public List<JobResponse> getAllJobs() {

        return jobService.getAllJobs();

    }

    @GetMapping("/{id}")
    public JobResponse getJobById(
            @PathVariable Long id) {

        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public JobResponse updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request) {

        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {

        return jobService.deleteJob(id);
    }
}