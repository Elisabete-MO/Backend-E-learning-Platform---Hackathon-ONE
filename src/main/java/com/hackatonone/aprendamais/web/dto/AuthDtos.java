package com.hackatonone.aprendamais.web.dto;
import com.hackatonone.aprendamais.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthDtos {
    public record RegisterRequest(
            @NotBlank @Size(min=2, max=120) String name,
            @Email @NotBlank String email,
            @NotBlank @Size(min=6, max=72) String password,
            @NotNull Role role
    ) {}

    public record LoginRequest(
            @Email @NotBlank String email,
            @NotBlank String password
    ) {}

    public record AuthResponse(
            String token,
            String tokenType,
            long expiresInSeconds
    ) {}
}
