package com.manish.code2career.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Long totalJobs;

    private Long totalApplications;

    private Long totalProblems;

    private Long revisionProblems;

    private Long easyProblems;

    private Long mediumProblems;

    private Long hardProblems;

    private Long appliedApplications;

    private Long oaClearedApplications;

    private Long interviewScheduledApplications;
}