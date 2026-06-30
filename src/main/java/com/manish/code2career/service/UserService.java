package com.manish.code2career.service;

import com.manish.code2career.dto.RegisterRequest;
import com.manish.code2career.dto.RegisterResponse;
import com.manish.code2career.entity.User;
import com.manish.code2career.exception.UserAlreadyExistsException;
import com.manish.code2career.repository.UserRepository;
import com.manish.code2career.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.manish.code2career.dto.LoginRequest;
import com.manish.code2career.dto.LoginResponse;
import com.manish.code2career.exception.InvalidCredentialsException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UserAlreadyExistsException(
                    "Email already exists"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("STUDENT")
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password");
        }

        String jwtToken =
                jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}