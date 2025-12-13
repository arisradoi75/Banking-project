package com.exemple.bankingproject.controller;


import com.exemple.bankingproject.dto.LoginRequest;
import com.exemple.bankingproject.dto.RegisterRequest;
import com.exemple.bankingproject.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        boolean ok = userService.register(request.getUsername() , request.getPassword());
        return ok ? "REGISTER OK" : "USERNAME EXIST";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean ok = userService.login(request.getUsername() , request.getPassword());
        return ok ? "LOGIN OK" : "LOGIN FAIL";
    }


}
