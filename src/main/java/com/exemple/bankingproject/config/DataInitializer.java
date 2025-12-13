package com.exemple.bankingproject.config;

import com.exemple.bankingproject.model.UserType;
import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(
                        new User("admin", "admin123", UserType.ADMIN)
                );
                System.out.println("ADMIN CREATED");
            }
        };
    }
}
