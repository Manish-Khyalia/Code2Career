package com.manish.code2career.controller;

import com.manish.code2career.dto.ResumeResponse;
import com.manish.code2career.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResumeResponse uploadResume(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return resumeService.uploadResume(file);
    }

    @GetMapping
    public List<ResumeResponse> getAllResumes() {

        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResumeResponse getResumeById(
            @PathVariable Long id) {

        return resumeService.getResumeById(id);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewResume(
            @PathVariable Long id)
            throws MalformedURLException {

        ResumeResponse resume = resumeService.getResumeById(id);

        Path path = Paths.get(resume.getFilePath());

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resume.getFileName() + "\""
                )
                .body(resource);

    }

    @DeleteMapping("/{id}")
    public String deleteResume(
            @PathVariable Long id) {

        return resumeService.deleteResume(id);
    }

}