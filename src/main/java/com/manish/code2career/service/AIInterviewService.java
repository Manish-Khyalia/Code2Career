package com.manish.code2career.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIInterviewService {

    public List<String> generateQuestions(String company,
                                          String role,
                                          String experience) {

        return List.of(

                "Tell me about yourself.",

                "Why do you want to join " + company + "?",

                "Explain your latest " + role + " project.",

                "What are the four pillars of OOP?",

                "Difference between HashMap and TreeMap?",

                "Explain JVM architecture.",

                "What is Spring Boot?",

                "Difference between REST and RESTful API?",

                "Explain SQL JOINs.",

                "Why should we hire you for this " + role + " role?"

        );

    }

}