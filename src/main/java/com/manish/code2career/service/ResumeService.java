package com.manish.code2career.service;

import com.manish.code2career.dto.ResumeResponse;
import com.manish.code2career.entity.Resume;
import com.manish.code2career.exception.ResumeNotFoundException;
import com.manish.code2career.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import com.manish.code2career.entity.User;
import com.manish.code2career.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public ResumeResponse uploadResume(
            MultipartFile file) throws IOException {

        String uploadDir = "uploads/";

        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);

        Files.write(path, file.getBytes());

        User user = getCurrentUser();

        Resume resume = Resume.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .filePath(path.toString())
                .uploadDate(LocalDate.now())
                .user(user)
                .build();

        Resume savedResume = resumeRepository.save(resume);

        return ResumeResponse.builder()
                .id(savedResume.getId())
                .fileName(savedResume.getFileName())
                .fileType(savedResume.getFileType())
                .filePath(savedResume.getFilePath())
                .uploadDate(savedResume.getUploadDate())
                .build();
    }

    public List<ResumeResponse> getAllResumes() {

        User user = getCurrentUser();

        return resumeRepository.findByUser(user)
                .stream()
                .map(resume -> ResumeResponse.builder()
                        .id(resume.getId())
                        .fileName(resume.getFileName())
                        .fileType(resume.getFileType())
                        .filePath(resume.getFilePath())
                        .uploadDate(resume.getUploadDate())
                        .build())
                .toList();
    }

    public ResumeResponse getResumeById(Long id) {

        User user = getCurrentUser();

        Resume resume = resumeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResumeNotFoundException(
                                "Resume not found"));

        return ResumeResponse.builder()
                .id(resume.getId())
                .fileName(resume.getFileName())
                .fileType(resume.getFileType())
                .filePath(resume.getFilePath())
                .uploadDate(resume.getUploadDate())
                .build();
    }

    public String deleteResume(Long id) {

        User user = getCurrentUser();

        Resume resume = resumeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResumeNotFoundException(
                                "Resume not found"));

        try {

            Files.deleteIfExists(Paths.get(resume.getFilePath()));

        }
        catch (IOException e) {

            throw new RuntimeException("Unable to delete resume file", e);

        }

        resumeRepository.delete(resume);

        return "Resume deleted successfully";
    }
}