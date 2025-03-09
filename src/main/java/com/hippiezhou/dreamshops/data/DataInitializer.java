package com.hippiezhou.dreamshops.data;

import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultUserIfNotExists();
    }

    private void createDefaultUserIfNotExists() {
        for (int i = 0; i < 5; i++) {
            String defaultEmail = "user" + i + "@example.com";
            if (userRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            User user = new User();
            user.setFirstName("User" + i);
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setPassword("password" + i);
            userRepository.save(user);
            System.out.println("User created: " + user);
        }
    }
}
