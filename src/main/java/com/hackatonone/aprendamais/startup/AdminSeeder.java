package com.hackatonone.aprendamais.startup;

import com.hackatonone.aprendamais.domain.enums.Role;
import com.hackatonone.aprendamais.domain.models.User;
import com.hackatonone.aprendamais.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile("!test")
@DependsOn("flyway") // só roda após as migrations
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationRunner {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    @Value("${seed.admin.email:}")     private String adminEmail;
    @Value("${seed.admin.password:}")  private String adminPassword;
    @Value("${seed.admin.name:Admin}") private String adminName;

    // opcional: atualiza role/active se já existir
    @Value("${seed.admin.update-if-exists:false}") private boolean updateIfExists;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (isBlank(adminEmail) || isBlank(adminPassword)) {
            log.info("AdminSeeder: seed ignorado (email/senha não configurados)");
            return;
        }

        users.findByEmailIgnoreCase(adminEmail).ifPresentOrElse(u -> {
            if (updateIfExists) {
                boolean changed = false;
                if (u.getRole() != Role.ADMIN) { u.setRole(Role.ADMIN); changed = true; }
                if (!u.isActive()) { u.setActive(true); changed = true; }
                if (changed) {
                    users.save(u);
                    log.info("AdminSeeder: admin existente ajustado (role/active) -> {}", adminEmail);
                } else {
                    log.info("AdminSeeder: admin já existia sem ajustes -> {}", adminEmail);
                }
            } else {
                log.info("AdminSeeder: admin já existe -> {}", adminEmail);
            }
        }, () -> {
            User u = User.builder()
                    .name(adminName)
                    .email(adminEmail)
                    .passwordHash(encoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .active(true) // importante!
                    .build();
            users.save(u);
            log.info("AdminSeeder: admin criado -> {}", adminEmail);
        });
    }

    private static boolean isBlank(String s) { return s == null || s.isBlank(); }
}
