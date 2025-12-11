package com.exemple.bankingproject.controller;

import com.exemple.bankingproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "loggin";
    }
@PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String email ,@RequestParam String password, Model model){
        if(userService.login(username , email , password)){
            return "redirect:/lista-transcatii";
        } else{
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
     public String registerPage(@RequestParam String username , @RequestParam String email , @RequestParam String password , Model model){
        if(userService.register(username , email , password)){
            model.addAttribute("succes" , "Cont creat cu succes!");
            return "register";
        } else{
            model.addAttribute("error" , "Username deja folosit");
            return "register";
        }
    }




}
