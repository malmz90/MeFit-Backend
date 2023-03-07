package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.User;
import com.example.mefitbackend.repositories.UserRepository;
import com.example.mefitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all Users.
     *
     * @return Iterable<User>
     */
    @GetMapping("list")
    public List<User> getUsers() {
        return userService.getUsers();
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User returnUser = new User();
        HttpStatus status;

        if (userRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnUser = userRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnUser, status);
    }*/
}

