package com.hackatonone.aprendamais.service;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.domain.models.User;
import com.hackatonone.aprendamais.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    @Transactional
    public User create(String name, String email, String rawPwd, Role role) {
        if (users.existsByEmailIgnoreCase(email)) throw new IllegalArgumentException("Email já cadastrado");
        User u = User.builder()
                .name(name).email(email)
                .passwordHash(encoder.encode(rawPwd))
                .role(role)
                .active(true)
                .build();
        return users.save(u);
    }

    @Transactional
    public User changeRole(UUID userId, Role newRole, Principal actor) {
        User u = users.findById(userId).orElseThrow();
        // Proteção: não permitir que o último ADMIN perca o papel de ADMIN
        if (u.getRole() == Role.ADMIN && newRole != Role.ADMIN) {
            long adminsAtivos = users.countByRoleAndActive(Role.ADMIN, true);
            if (adminsAtivos <= 1) throw new IllegalArgumentException("Não é possível remover o único ADMIN ativo.");
            // opcional: impedir que o admin remova o próprio ADMIN
            if (actor != null && u.getEmail().equalsIgnoreCase(actor.getName()))
                throw new IllegalArgumentException("Você não pode remover seu próprio papel de ADMIN.");
        }
        u.setRole(newRole);
        return users.save(u);
    }

    @Transactional
    public User setActive(UUID userId, boolean active, Principal actor) {
        User u = users.findById(userId).orElseThrow();

        // proteção: não permitir desativar o último ADMIN ativo
        if (!active && u.getRole() == Role.ADMIN) {
            long adminsAtivos = users.countByRoleAndActive(Role.ADMIN, true);
            if (adminsAtivos <= 1) throw new IllegalArgumentException("Não é possível desativar o único ADMIN ativo.");
        }
        // proteção: não desativar a si mesmo
        if (!active && actor != null && u.getEmail().equalsIgnoreCase(actor.getName())) {
            throw new IllegalArgumentException("Você não pode desativar a si próprio.");
        }

        u.setActive(active);
        return users.save(u);
    }

    public Optional<User> get(UUID id) {
        return users.findById(id);
    }

    public List<User> listAll() {
        return users.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

}
