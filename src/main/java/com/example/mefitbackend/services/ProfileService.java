package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(Integer id) {
        return profileRepository.findById(id).get();
    }

    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
