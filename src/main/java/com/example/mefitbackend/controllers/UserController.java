package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all users")
    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User does not exist with supplied ID",
                    content = @Content)
    })
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

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Not Authorized",
                    content = @Content)
    })
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

    @Operation(summary = "Update an existing user by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "User successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {

        if(id != user.getUser_id()) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete an user by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

