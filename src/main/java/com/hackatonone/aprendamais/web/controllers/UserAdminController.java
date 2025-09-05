package com.hackatonone.aprendamais.web.controllers;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.service.AdminUserService;
import com.hackatonone.aprendamais.web.dto.UserRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin - Users")
public class UserAdminController {

    private final AdminUserService svc;

    /* ===== DTOs ===== */

    public record AdminCreateReq(
            @NotBlank String name,
            @Email String email,
            @NotBlank String password,
            Role role
    ) {
    }

    public record ActiveReq(boolean active) {
    }

    /* ===== Endpoints ===== */

    @Operation(summary = "Criar usuário (ADMIN, INSTRUCTOR ou STUDENT)")
    @PostMapping
    public ResponseEntity<UserRes> create(@RequestBody AdminCreateReq r) {
        var u = svc.create(r.name(), r.email(), r.password(), r.role());
        return ResponseEntity.ok(UserRes.of(u));
    }

    @Operation(summary = "Alterar role do usuário")
    @PostMapping("/{id}/role")
    public ResponseEntity<UserRes> changeRole(@PathVariable UUID id, @RequestParam Role role, Principal me) {
        var u = svc.changeRole(id, role, me);
        return ResponseEntity.ok(UserRes.of(u));
    }

    @Operation(summary = "Ativar/Desativar usuário")
    @PatchMapping("/{id}/active")
    public ResponseEntity<UserRes> setActive(@PathVariable UUID id, @RequestBody ActiveReq req, Principal me) {
        var u = svc.setActive(id, req.active(), me);
        return ResponseEntity.ok(UserRes.of(u));
    }


    @Operation(summary = "Buscar um usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserRes> get(@PathVariable UUID id) {
        return svc.get(id).map(UserRes::of)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todos os usuários (sem paginação)")
    @GetMapping("/all")
    public ResponseEntity<List<UserRes>> all() {
        var list = svc.listAll().stream().map(UserRes::of).toList();
        return ResponseEntity.ok(list);
    }
}