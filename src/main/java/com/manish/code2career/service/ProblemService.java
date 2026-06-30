package com.manish.code2career.service;

import com.manish.code2career.dto.ProblemRequest;
import com.manish.code2career.dto.ProblemResponse;
import com.manish.code2career.entity.Problem;
import com.manish.code2career.exception.ProblemNotFoundException;
import com.manish.code2career.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemResponse addProblem(
            ProblemRequest request) {

        Problem problem = Problem.builder()
                .problemName(request.getProblemName())
                .platform(request.getPlatform())
                .difficulty(request.getDifficulty())
                .topic(request.getTopic())
                .revisionStatus(request.getRevisionStatus())
                .notes(request.getNotes())
                .build();

        Problem savedProblem =
                problemRepository.save(problem);

        return ProblemResponse.builder()
                .id(savedProblem.getId())
                .problemName(savedProblem.getProblemName())
                .platform(savedProblem.getPlatform())
                .difficulty(savedProblem.getDifficulty())
                .topic(savedProblem.getTopic())
                .revisionStatus(savedProblem.getRevisionStatus())
                .notes(savedProblem.getNotes())
                .build();
    }

    public List<ProblemResponse> getAllProblems() {

        return problemRepository.findAll()
                .stream()
                .map(problem -> ProblemResponse.builder()
                        .id(problem.getId())
                        .problemName(problem.getProblemName())
                        .platform(problem.getPlatform())
                        .difficulty(problem.getDifficulty())
                        .topic(problem.getTopic())
                        .revisionStatus(problem.getRevisionStatus())
                        .notes(problem.getNotes())
                        .build())
                .toList();
    }

    public ProblemResponse getProblemById(Long id) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found"));

        return ProblemResponse.builder()
                .id(problem.getId())
                .problemName(problem.getProblemName())
                .platform(problem.getPlatform())
                .difficulty(problem.getDifficulty())
                .topic(problem.getTopic())
                .revisionStatus(problem.getRevisionStatus())
                .notes(problem.getNotes())
                .build();
    }

    public ProblemResponse updateProblem(
            Long id,
            ProblemRequest request) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found"));

        problem.setProblemName(request.getProblemName());
        problem.setPlatform(request.getPlatform());
        problem.setDifficulty(request.getDifficulty());
        problem.setTopic(request.getTopic());
        problem.setRevisionStatus(request.getRevisionStatus());
        problem.setNotes(request.getNotes());

        Problem updatedProblem =
                problemRepository.save(problem);

        return ProblemResponse.builder()
                .id(updatedProblem.getId())
                .problemName(updatedProblem.getProblemName())
                .platform(updatedProblem.getPlatform())
                .difficulty(updatedProblem.getDifficulty())
                .topic(updatedProblem.getTopic())
                .revisionStatus(updatedProblem.getRevisionStatus())
                .notes(updatedProblem.getNotes())
                .build();
    }

    public String deleteProblem(Long id) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found"));

        problemRepository.delete(problem);

        return "Problem deleted successfully";
    }

    public List<ProblemResponse> getProblemsByTopic(
            String topic) {

        return problemRepository.findByTopic(topic)
                .stream()
                .map(problem -> ProblemResponse.builder()
                        .id(problem.getId())
                        .problemName(problem.getProblemName())
                        .platform(problem.getPlatform())
                        .difficulty(problem.getDifficulty())
                        .topic(problem.getTopic())
                        .revisionStatus(problem.getRevisionStatus())
                        .notes(problem.getNotes())
                        .build())
                .toList();
    }

    public List<ProblemResponse> getProblemsByDifficulty(
            String difficulty) {

        return problemRepository.findByDifficulty(difficulty)
                .stream()
                .map(problem -> ProblemResponse.builder()
                        .id(problem.getId())
                        .problemName(problem.getProblemName())
                        .platform(problem.getPlatform())
                        .difficulty(problem.getDifficulty())
                        .topic(problem.getTopic())
                        .revisionStatus(problem.getRevisionStatus())
                        .notes(problem.getNotes())
                        .build())
                .toList();
    }

    public List<ProblemResponse> getProblemsByRevisionStatus(
            Boolean revisionStatus) {

        return problemRepository.findByRevisionStatus(revisionStatus)
                .stream()
                .map(problem -> ProblemResponse.builder()
                        .id(problem.getId())
                        .problemName(problem.getProblemName())
                        .platform(problem.getPlatform())
                        .difficulty(problem.getDifficulty())
                        .topic(problem.getTopic())
                        .revisionStatus(problem.getRevisionStatus())
                        .notes(problem.getNotes())
                        .build())
                .toList();
    }
}