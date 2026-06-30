package com.manish.code2career.controller;

import com.manish.code2career.dto.ApplicationRequest;
import com.manish.code2career.dto.ApplicationResponse;
import com.manish.code2career.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationResponse addApplication(
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.addApplication(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {

        return applicationService.getAllApplications();

    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(
            @PathVariable Long id) {

        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.updateApplication(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(
            @PathVariable Long id) {

        return applicationService.deleteApplication(id);
    }

}