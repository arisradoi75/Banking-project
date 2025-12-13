package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.model.UserType;
import com.exemple.bankingproject.repository.UserRepository;
import com.exemple.bankingproject.config.PasswordConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean isAdmin(String adminUsername) {
        User admin = userRepository.findByUsername(adminUsername);
        return admin != null && admin.getUserType() == UserType.ADMIN;
    }


    public boolean register(String username , String password){
        if(userRepository.findByUsername(username) != null){
            return false;
        }
        String hashedPassword = passwordEncoder.encode(password);

        userRepository.save(new User(username,hashedPassword , UserType.USER));
        return true;
    }

    public boolean login(String username , String password){
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password , user.getPassword());
    }

    public boolean deleteUser(String adminUsername , String usernameToDelete){
        if(!isAdmin(adminUsername)){
            return false;
        }
        User user = userRepository.findByUsername(usernameToDelete);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }



}
