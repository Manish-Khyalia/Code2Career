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

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeResponse uploadResume(
            MultipartFile file) throws IOException {

        String uploadDir = "uploads/";

        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path path = Paths.get(uploadDir + fileName);

        Files.write(path, file.getBytes());

        Resume resume = Resume.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .filePath(path.toString())
                .uploadDate(LocalDate.now())
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

        return resumeRepository.findAll()
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

        Resume resume = resumeRepository.findById(id)
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

        Resume resume = resumeRepository.findById(id)
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