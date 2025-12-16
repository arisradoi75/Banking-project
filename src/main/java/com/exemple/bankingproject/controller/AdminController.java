package com.exemple.bankingproject.controller;

import com.exemple.bankingproject.repository.UserRepository;
import com.exemple.bankingproject.service.JwtService;
import com.exemple.bankingproject.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final JwtService jwtService;

    public AdminController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @DeleteMapping("/delete")
    public String deleteUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String username
    ) {
        String token = authHeader.replace("Bearer ", "");
        Claims claims = jwtService.parseToken(token);

        String role = claims.get("role", String.class);

        if (!"ADMIN".equals(role)) {
            return "ACCESS DENIED";
        }

        userService.deleteUser(username);
        return "USER DELETED";
    }
}

