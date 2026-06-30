package com.manish.code2career.controller;

import com.manish.code2career.dto.LoginRequest;
import com.manish.code2career.dto.LoginResponse;
import com.manish.code2career.dto.RegisterRequest;
import com.manish.code2career.dto.RegisterResponse;
import com.manish.code2career.entity.User;
import com.manish.code2career.security.CustomUserDetailsService;
import com.manish.code2career.security.JwtService;
import com.manish.code2career.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return userService.login(request);
    }



}