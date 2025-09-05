package com.hackatonone.aprendamais.web.dto;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.domain.models.User;

import java.time.Instant;
import java.util.UUID;

public record UserRes(UUID id, String name, String email, Role role, boolean active,
                      Instant createdAt, Instant lastLogin) {
    public static UserRes of(User u) {
        return new UserRes(u.getId(), u.getName(), u.getEmail(), u.getRole(),
                u.isActive(), u.getCreatedAt(), u.getLastLogin());
    }
}