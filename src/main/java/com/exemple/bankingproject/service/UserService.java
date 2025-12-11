package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean register(String username , String email , String password){
        if(userRepository.findByUsername(username) != null){
            return false;
        }
        userRepository.save(new User(username,email,password));
        return true;
    }

    public boolean login(String username, String email , String password){
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password) && user.getEmail().equals(email);
    }

}
