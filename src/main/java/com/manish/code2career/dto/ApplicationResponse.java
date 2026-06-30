package com.manish.code2career.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private Long id;

    private String companyName;

    private String jobRole;

    private String applicationDate;

    private String status;

    private String notes;

}