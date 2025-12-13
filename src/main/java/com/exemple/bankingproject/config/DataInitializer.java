package com.exemple.bankingproject.config;

import com.exemple.bankingproject.model.UserType;
import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createOrUpdateAdmin(UserRepository repo,
                                          PasswordEncoder encoder) {
        return args -> {
            User admin = repo.findByUsername("admin");

            if (admin == null) {
                admin = new User(
                        "admin",
                        encoder.encode("admin123"),
                        UserType.ADMIN
                );
                repo.save(admin);
                System.out.println("ADMIN CREATED");
            } else {
                admin.setPassword(encoder.encode("admin123"));
                admin.setUserType(UserType.ADMIN);
                repo.save(admin);
                System.out.println("ADMIN UPDATED");
            }
        };
    }
}

