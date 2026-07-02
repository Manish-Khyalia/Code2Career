package com.manish.code2career.controller;

import com.manish.code2career.service.AIInterviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIInterviewController {

    private final AIInterviewService aiInterviewService;

    public AIInterviewController(AIInterviewService aiInterviewService) {

        this.aiInterviewService = aiInterviewService;

    }

    @GetMapping("/ai/interview")
    public List<String> generateQuestions(

            @RequestParam String company,
            @RequestParam String role,
            @RequestParam String experience

    ) {

        return aiInterviewService.generateQuestions(
                company,
                role,
                experience
        );

    }

}