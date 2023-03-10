package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.ProfileDTO;
import com.example.mefitbackend.mappers.ProfileMapper;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.services.ProfileService;
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
@RequestMapping("api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileMapper profileMapper;

    private HttpStatus httpStatus;

    /**
     * Get all Profiles.
     *
     * @return Iterable<Profile>
     */

    @Operation(summary = "Get all profiles")
    @GetMapping("profiles")
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @Operation(summary = "Get profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Profile does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable int id) {
        ProfileDTO profileDTO = profileMapper.toProfileDto(profileService.getProfileById(id));
        HttpStatus status;

        if(profileDTO != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(profileDTO, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }

    }

    @Operation(summary = "Create a new profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created profile",
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
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            profile = profileService.saveProfile(profile);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(profile, httpStatus);
    }

    @Operation(summary = "Updating an existing profile by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Profile not found with supplied ID",
                    content = @Content)
    })
    @PatchMapping("{id}")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, @PathVariable int id) {
        if(id != profile.getProfile_id())
            return ResponseEntity.badRequest().build();
        profileService.updateProfile(profile);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a profile by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable int id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

