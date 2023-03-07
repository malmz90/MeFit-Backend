package com.example.mefitbackend.services;

import com.example.mefitbackend.models.User;
import com.example.mefitbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers(){
       return userRepository.findAll();
    };

}
