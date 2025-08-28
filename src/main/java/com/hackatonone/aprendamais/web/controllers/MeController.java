package com.hackatonone.aprendamais.web.controllers;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.domain.models.User;
import com.hackatonone.aprendamais.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Me")
public class MeController {

    private final UserRepository users;

    public record MeDto(UUID id, String name, String email, Role role) {}

    @GetMapping("/me")
    @Operation(summary = "Dados do usuário logado",
            description = "Retorna id, name, email e role do usuário autenticado")
    public ResponseEntity<MeDto> me(Principal principal) {
        // principal.getName() retorna o 'email' definido no JwtAuthFilter
        String email = principal.getName();

        User u = users.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));

        return ResponseEntity.ok(new MeDto(u.getId(), u.getName(), u.getEmail(), u.getRole()));
    }
}
