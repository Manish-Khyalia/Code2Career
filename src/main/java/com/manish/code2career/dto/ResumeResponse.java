package com.manish.code2career.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResumeResponse {

    private Long id;

    private String fileName;

    private String fileType;

    private String filePath;

    private LocalDate uploadDate;
}