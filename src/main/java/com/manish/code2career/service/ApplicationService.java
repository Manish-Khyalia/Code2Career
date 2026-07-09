package com.manish.code2career.service;

import com.manish.code2career.dto.ApplicationRequest;
import com.manish.code2career.dto.ApplicationResponse;
import com.manish.code2career.entity.Application;
import com.manish.code2career.exception.ApplicationNotFoundException;
import com.manish.code2career.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.manish.code2career.entity.User;
import com.manish.code2career.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public ApplicationResponse addApplication(
            ApplicationRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Application application = Application.builder()
                .companyName(request.getCompanyName())
                .jobRole(request.getJobRole())
                .applicationDate(request.getApplicationDate())
                .status(request.getStatus())
                .notes(request.getNotes())
                .user(user)
                .build();

        Application savedApplication =
                applicationRepository.save(application);

        return ApplicationResponse.builder()
                .id(savedApplication.getId())
                .companyName(savedApplication.getCompanyName())
                .jobRole(savedApplication.getJobRole())
                .applicationDate(savedApplication.getApplicationDate())
                .status(savedApplication.getStatus())
                .notes(savedApplication.getNotes())
                .build();
    }

    public List<ApplicationResponse> getAllApplications() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return applicationRepository.findByUser(user)
                .stream()
                .map(application -> ApplicationResponse.builder()
                        .id(application.getId())
                        .companyName(application.getCompanyName())
                        .jobRole(application.getJobRole())
                        .applicationDate(application.getApplicationDate())
                        .status(application.getStatus())
                        .notes(application.getNotes())
                        .build())
                .toList();
    }

    public ApplicationResponse getApplicationById(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Application application = applicationRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found"));

        return ApplicationResponse.builder()
                .id(application.getId())
                .companyName(application.getCompanyName())
                .jobRole(application.getJobRole())
                .applicationDate(application.getApplicationDate())
                .status(application.getStatus())
                .notes(application.getNotes())
                .build();
    }

    public ApplicationResponse updateApplication(
            Long id,
            ApplicationRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Application application = applicationRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found"));

        application.setCompanyName(request.getCompanyName());
        application.setJobRole(request.getJobRole());
        application.setApplicationDate(request.getApplicationDate());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());

        Application updatedApplication =
                applicationRepository.save(application);

        return ApplicationResponse.builder()
                .id(updatedApplication.getId())
                .companyName(updatedApplication.getCompanyName())
                .jobRole(updatedApplication.getJobRole())
                .applicationDate(updatedApplication.getApplicationDate())
                .status(updatedApplication.getStatus())
                .notes(updatedApplication.getNotes())
                .build();
    }

    public String deleteApplication(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Application application = applicationRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found"));

        applicationRepository.delete(application);

        return "Application deleted successfully";
    }
}