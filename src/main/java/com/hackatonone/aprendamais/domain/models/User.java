package com.hackatonone.aprendamais.domain.models;

import com.hackatonone.aprendamais.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @UuidGenerator
    private UUID id;

    @Column(nullable=false, length=120)
    private String name;

    @Column(nullable=false, length=160, unique=true)
    private String email;

    @Column(name="password_hash", nullable=false, length=255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private Role role;

    @Builder.Default
    @Column(nullable=false)
    private boolean active = true;

    private Instant createdAt;
    private Instant lastLogin;

    @PrePersist void prePersist() {
        createdAt = Instant.now();
        if (email != null) email = email.toLowerCase();
    }
}
