package com.hackatonone.aprendamais.service;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.domain.models.User;
import com.hackatonone.aprendamais.repository.UserRepository;
import com.hackatonone.aprendamais.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public String registerStudent(String name, String email, String rawPwd) {
        if (users.existsByEmailIgnoreCase(email))
            throw new IllegalArgumentException("Email já cadastrado");

        User u = User.builder()
                .name(name)
                .email(email)
                .passwordHash(encoder.encode(rawPwd))
                .role(Role.STUDENT) // travado
                .build();

        users.save(u);
        return jwt.generate(u.getEmail(), u.getRole());
    }

    public String login(String email, String rawPwd) {
        User u = users.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!u.isActive() || !encoder.matches(rawPwd, u.getPasswordHash()))
            throw new IllegalArgumentException("Credenciais inválidas");

        u.setLastLogin(Instant.now());
        users.save(u);

        return jwt.generate(u.getEmail(), u.getRole());
    }
}