package com.hackatonone.aprendamais.web.controllers;

import com.hackatonone.aprendamais.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;

    @Value("${app.jwt.expiration-minutes:60}") long expMin;

    public record RegisterReq(@NotBlank String name, @Email String email, @NotBlank String password) {}
    public record LoginReq(@Email String email, @NotBlank String password) {}
    public record AuthRes(String token, String tokenType, long expiresIn) {}

    @Operation(summary = "Registro de estudante", description = "Cria um usuário com role STUDENT")
    @PostMapping("/register")
    public ResponseEntity<AuthRes> register(@RequestBody RegisterReq r) {
        String token = auth.registerStudent(r.name(), r.email(), r.password());
        return ResponseEntity.ok(new AuthRes(token, "Bearer", expMin * 60));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRes> login(@RequestBody LoginReq r) {
        String token = auth.login(r.email(), r.password());
        return ResponseEntity.ok(new AuthRes(token, "Bearer", expMin * 60));
    }
}
