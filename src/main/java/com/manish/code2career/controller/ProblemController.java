package com.manish.code2career.controller;

import com.manish.code2career.dto.ProblemRequest;
import com.manish.code2career.dto.ProblemResponse;
import com.manish.code2career.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ProblemResponse addProblem(
            @Valid @RequestBody ProblemRequest request) {

        return problemService.addProblem(request);
    }

    @GetMapping
    public List<ProblemResponse> getAllProblems() {

        return problemService.getAllProblems();

    }

    @GetMapping("/{id}")
    public ProblemResponse getProblemById(
            @PathVariable Long id) {

        return problemService.getProblemById(id);
    }

    @PutMapping("/{id}")
    public ProblemResponse updateProblem(
            @PathVariable Long id,
            @Valid @RequestBody ProblemRequest request) {

        return problemService.updateProblem(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProblem(
            @PathVariable Long id) {

        return problemService.deleteProblem(id);
    }

    @GetMapping("/topic/{topic}")
    public List<ProblemResponse> getProblemsByTopic(
            @PathVariable String topic) {

        return problemService.getProblemsByTopic(topic);
    }

    @GetMapping("/difficulty/{difficulty}")
    public List<ProblemResponse> getProblemsByDifficulty(
            @PathVariable String difficulty) {

        return problemService.getProblemsByDifficulty(difficulty);
    }

    @GetMapping("/revision/{status}")
    public List<ProblemResponse> getProblemsByRevisionStatus(
            @PathVariable Boolean status) {

        return problemService.getProblemsByRevisionStatus(status);
    }

}