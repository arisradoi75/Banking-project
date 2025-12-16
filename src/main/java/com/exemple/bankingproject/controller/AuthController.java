package com.exemple.bankingproject.controller;


import com.exemple.bankingproject.dto.LoginRequest;
import com.exemple.bankingproject.dto.RegisterRequest;
import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.repository.UserRepository;
import com.exemple.bankingproject.service.JwtService;
import com.exemple.bankingproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private UserService userService;

    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        boolean ok = userService.register(request.getUsername() , request.getPassword());
        return ok ? "REGISTER OK" : "USERNAME EXIST";
    }
    @PostMapping("/login")
    public Map<String , String> login(@RequestBody LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("LOGIN FAILED");
        }
        String token = jwtService.generateToken(user.getUsername(), user.getUserType().name());
        return Map.of("token", token);
    }


}
