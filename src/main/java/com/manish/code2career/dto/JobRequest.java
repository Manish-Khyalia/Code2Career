package com.manish.code2career.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String jobRole;

    private String location;

    private Double salary;

    private String status;

    private String applicationLink;

    private String deadline;
}