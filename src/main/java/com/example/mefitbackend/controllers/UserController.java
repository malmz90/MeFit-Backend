package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.UserGetDTO;
import com.example.mefitbackend.dto.UserPostDTO;
import com.example.mefitbackend.mappers.UserMapper;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
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
    public ResponseEntity<UserGetDTO> getUser(@PathVariable int id) {
        HttpStatus status;
        UserGetDTO user = userMapper.userToUserGetDTO(userService.getUserById(id));

        if (user != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(user, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
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
    @GetMapping("/keycloak")
    public ResponseEntity<UserGetDTO> findUserByKeyCloakId(@AuthenticationPrincipal Jwt principal) {
        String keycloakId = principal.getClaimAsString("sub");
        HttpStatus status;
        User user = userService.getUserByKeyCloakId(keycloakId);
        if (user != null) {
            UserGetDTO userGetDTO = userMapper.userToUserGetDTO(user);
            status = HttpStatus.OK;
            return new ResponseEntity<>(userGetDTO, status);
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

    @PostMapping()
    public ResponseEntity<User> add(@AuthenticationPrincipal Jwt principal) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            UserPostDTO userPostDTO = new UserPostDTO();
            userPostDTO.setUsername(principal.getClaimAsString("preferred_username"));
            userPostDTO.setKeyCloakId(principal.getClaimAsString("sub"));
            userPostDTO.setFirstName(principal.getClaimAsString("given_name"));
            userPostDTO.setLastName(principal.getClaimAsString("family_name"));
            userPostDTO.setEmail(principal.getClaimAsString("email"));

            List<String> roles = principal.getClaimAsStringList("roles");
            userPostDTO.setAdmin(roles.contains("admin"));
            userPostDTO.setContributor((roles.contains("contributor")));

            User user = userMapper.userPostDTOtoUser(userPostDTO);
            user = userService.saveUser(user);
            httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(user, httpStatus);
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(null, httpStatus);
        }
    }

 /*   @PostMapping
    public ResponseEntity<User> add(@RequestBody UserPostDTO userPostDTO) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            User user = userMapper.userPostDTOtoUser(userPostDTO);
            user = userService.saveUser(user);
            httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(user, httpStatus);
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(null, httpStatus);
        }
    }*/

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
    public ResponseEntity<UserGetDTO> updateUser(@PathVariable int id, @RequestBody Map<String, Object> partialUpdate) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        if (partialUpdate.containsKey("username")) {
            existingUser.setUsername((String) partialUpdate.get("username"));
        }
        if (partialUpdate.containsKey("email")) {
            existingUser.setEmail((String) partialUpdate.get("email"));
        }
        if (partialUpdate.containsKey("admin")) {
            existingUser.setAdmin((boolean) partialUpdate.get("admin"));
        }
        if (partialUpdate.containsKey("contributor")) {
            existingUser.setContributor((boolean) partialUpdate.get("contributor"));
        }

        User updatedUser = userService.updateUser(existingUser);
        UserGetDTO updatedUserDTO = userMapper.userToUserGetDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }





    @Operation(summary = "Delete an user by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}

