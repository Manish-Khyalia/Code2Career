package com.manish.code2career.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String jobRole;

    private String applicationDate;

    private String status;

    private String notes;

}