package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.User;
import com.exemple.bankingproject.model.UserType;
import com.exemple.bankingproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private boolean isAdmin(String adminUsername) {
        User admin = userRepository.findByUsername(adminUsername);
        return admin != null && admin.getUserType() == UserType.ADMIN;
    }


    public boolean register(String username , String password){
        if(userRepository.findByUsername(username) != null){
            return false;
        }
        userRepository.save(new User(username,password , UserType.USER));
        return true;
    }

    public boolean login(String username , String password){
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
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
