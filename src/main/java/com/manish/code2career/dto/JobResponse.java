package com.manish.code2career.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResponse {

    private Long id;

    private String companyName;

    private String jobRole;

    private String location;

    private Double salary;

    private String status;

    private String applicationLink;

    private String deadline;
}