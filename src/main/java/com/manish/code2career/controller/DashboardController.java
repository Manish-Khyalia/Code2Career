package com.manish.code2career.controller;

import com.manish.code2career.dto.DashboardResponse;
import com.manish.code2career.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();

    }
}