package com.manish.code2career.service;

import com.manish.code2career.dto.ProblemRequest;
import com.manish.code2career.dto.ProblemResponse;
import com.manish.code2career.entity.Problem;
import com.manish.code2career.exception.ProblemNotFoundException;
import com.manish.code2career.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.manish.code2career.entity.User;
import com.manish.code2career.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public ProblemResponse addProblem(
            ProblemRequest request) {

        User user = getCurrentUser();

        Problem problem = Problem.builder()
                .problemName(request.getProblemName())
                .platform(request.getPlatform())
                .difficulty(request.getDifficulty())
                .topic(request.getTopic())
                .revisionStatus(request.getRevisionStatus())
                .notes(request.getNotes())
                .user(user)
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

        User user = getCurrentUser();

        return problemRepository.findByUser(user)
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

        User user = getCurrentUser();

        Problem problem = problemRepository
                .findByIdAndUser(id, user)
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

        User user = getCurrentUser();

        Problem problem = problemRepository
                .findByIdAndUser(id, user)
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

        User user = getCurrentUser();

        Problem problem = problemRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found"));

        problemRepository.delete(problem);

        return "Problem deleted successfully";
    }

    public List<ProblemResponse> getProblemsByTopic(String topic) {

        User user = getCurrentUser();

        return problemRepository.findByUserAndTopic(user, topic)
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

        User user = getCurrentUser();

        return problemRepository.findByUserAndDifficulty(user, difficulty)
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

        User user = getCurrentUser();

        return problemRepository.findByUserAndRevisionStatus(user, revisionStatus)
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