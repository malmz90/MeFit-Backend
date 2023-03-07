package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.User;
import com.example.mefitbackend.repositories.UserRepository;
import com.example.mefitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    private HttpStatus httpStatus;

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        HttpStatus status;
        User user = userService.getUserById(id);

        if (user != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(user, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            user = userService.saveUser(user);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(user, httpStatus);
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

