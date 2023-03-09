package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.repositories.UserRepository;
import com.example.mefitbackend.services.ProfileService;
import com.example.mefitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Get all Profiles.
     *
     * @return Iterable<Profile>
     */

    // Get all profiles
    @GetMapping("profiles")
    public List<Profile> getProfiles() {
        return profileService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable int id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        Profile addProfile = profileService.add(profile);
        URI location = URI.create("profile/" + addProfile.getProfile_id());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, @PathVariable int id) {
        if(id != profile.getProfile_id())
            return ResponseEntity.badRequest().build();
        profileService.update(profile);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable int id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

