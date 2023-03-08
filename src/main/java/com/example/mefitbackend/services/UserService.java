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

    public User getUserById(int id) {return userRepository.findById(id).orElse(null); }


    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}
