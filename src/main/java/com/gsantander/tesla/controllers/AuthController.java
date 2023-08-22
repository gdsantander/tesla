package com.gsantander.tesla.controllers;

import java.util.Optional;

import com.gsantander.tesla.classes.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsantander.tesla.enums.TokenType;
import com.gsantander.tesla.jwt.JwtService;
import com.gsantander.tesla.model.TslRefreshToken;
import com.gsantander.tesla.model.TslUser;
import com.gsantander.tesla.services.AuthService;
import com.gsantander.tesla.services.RefreshTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(this.refreshTokenService.refreshToken(request.getRefreshToken()));
    }

}