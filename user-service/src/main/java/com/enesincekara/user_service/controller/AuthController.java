package com.enesincekara.user_service.controller;

import com.enesincekara.user_service.dto.LoginRequest;
import com.enesincekara.user_service.dto.TokenResponse;
import com.enesincekara.user_service.dto.UserDto;
import com.enesincekara.user_service.security.JwtService;
import com.enesincekara.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        UserDto user = userService.findByEmail(request.email());

        if (!userService.checkPassword(request.email(), request.password())) {
            throw new RuntimeException("Email or password is incorrect.");
        }

        String token = jwtService.generateToken(user.email());
        return ResponseEntity.ok(new TokenResponse(token));
    }}
