package com.hippiezhou.dreamshops.data;

import com.hippiezhou.dreamshops.model.Role;
import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_USER", "ROLE_ADMIN");
        createDefaultRoleIfNotExists(defaultRoles);

        createDefaultUserIfNotExists();
        createDefaultAdminIfNotExists();
    }

    private void createDefaultUserIfNotExists() {
        Role userRole = roleRepository.findByName("ROLE_USER").get();

        for (int i = 0; i < 5; i++) {
            String defaultEmail = "user" + i + "@example.com";
            if (userRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            User user = new User();
            user.setFirstName("User" + i);
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("password" + i));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
            System.out.println("User created: " + user);
        }
    }

    private void createDefaultAdminIfNotExists() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();

        for (int i = 0; i < 2; i++) {
            String defaultEmail = "admin" + i + "@example.com";
            if (userRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            User user = new User();
            user.setFirstName("Admin" + i);
            user.setLastName("Admin" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("password" + i));
            user.setRoles(Set.of(adminRole));
            userRepository.save(user);
            System.out.println("Admin created: " + user);
        }
    }

    private void createDefaultRoleIfNotExists(Set<String> roles) {
        roles.stream()
            .filter(role -> roleRepository.findByName(role).isEmpty())
            .map(Role::new).forEach(roleRepository::save);
    }
}
