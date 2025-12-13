package com.exemple.bankingproject.controller;

import com.exemple.bankingproject.repository.UserRepository;
import com.exemple.bankingproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private UserService userService;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String adminUsername,
                             @RequestParam String username) {

        System.out.println("ADMIN CONTROLLER CALLED");
        System.out.println("adminUsername = " + adminUsername);
        System.out.println("username = " + username);

        return userService.deleteUser(adminUsername , username)
                ? "USER DELETED"
                : "ACCES DENIED OR USER NOT FOUND";
    }

}
