package com.manish.code2career.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemResponse {

    private Long id;

    private String problemName;

    private String platform;

    private String difficulty;

    private String topic;

    private Boolean revisionStatus;

    private String notes;

}