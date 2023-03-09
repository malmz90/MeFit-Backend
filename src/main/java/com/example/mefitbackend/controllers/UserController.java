package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.User;
import com.example.mefitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {

        if(id != user.getUser_id()) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

