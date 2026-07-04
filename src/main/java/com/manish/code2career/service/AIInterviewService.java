package com.manish.code2career.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AIInterviewService {

    private final RestClient restClient;

    @Value("${openrouter.api.key}")
    private String apiKey;

    public AIInterviewService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<String> generateQuestions(
            String company,
            String role,
            String experience
    ) {
        try {

            String prompt = """
You are a Senior Technical Interviewer at %s.

Generate exactly 10 interview questions for a %s candidate with %s experience interviewing at %s.

Requirements:
- 5 Java/Backend/Role-specific technical questions.
- 2 DSA questions commonly asked by %s.
- 1 question about projects.
- 2 HR/Behavioral questions.
- Questions should be suitable for %s experience.
- Avoid repeating similar questions.
- Return only the questions.
- One question per line.
- Do not number them.
- Do not use markdown."""
                    .formatted(
                            company,
                            role,
                            experience,
                            company,
                            role,
                            experience
                    );

            Map<String, Object> requestBody = Map.of(

                    "model", "google/gemma-4-26b-a4b-it:free",

                    "messages",
                    List.of(
                            Map.of(
                                    "role", "user",
                                    "content", prompt
                            )
                    )

            );

            Map response =
                    restClient.post()
                            .uri("https://openrouter.ai/api/v1/chat/completions")
                            .header("Authorization", "Bearer " + apiKey)
                            .header("Content-Type", "application/json")
                            .header("HTTP-Referer", "http://localhost:5173")
                            .header("X-Title", "Code2Career")
                            .body(requestBody)
                            .retrieve()
                            .body(Map.class);
//            System.out.println(response);

            Map choice =
                    (Map) ((List) response.get("choices")).get(0);

            Map message =
                    (Map) choice.get("message");

            String text =
                    (String) message.get("content");

            return text.lines()
                    .map(String::trim)
                    .map(line -> line.replaceFirst("^\\d+\\.\\s*", ""))
                    .map(line -> line.replaceFirst("^-\\s*", ""))
                    .filter(line -> !line.isBlank())
                    .toList();

        }
        catch (Exception e) {

//            e.printStackTrace();
            System.out.println("AI API Error: " + e.getMessage());

            return List.of(
                    "The AI Interview service is temporarily unavailable.",
                    "Please try again after a few moments."
            );

        }


    }

}