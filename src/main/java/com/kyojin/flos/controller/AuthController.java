package com.kyojin.flos.controller;

import com.kyojin.flos.dto.auth.AuthResponse;
import com.kyojin.flos.dto.auth.LoginRequest;
import com.kyojin.flos.dto.auth.RegisterRequest;
import com.kyojin.flos.service.AuthService;
import com.kyojin.flos.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<AuthResponse>> register(@RequestBody @Valid RegisterRequest request) {
        return Response.success(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthResponse>> login(@RequestBody LoginRequest request) {
        return Response.success(authService.login(request));
    }

}
