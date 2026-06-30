package com.manish.code2career.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProblemRequest {

    @NotBlank
    private String problemName;

    @NotBlank
    private String platform;

    @NotBlank
    private String difficulty;

    @NotBlank
    private String topic;

    private Boolean revisionStatus;

    private String notes;

}