package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProfileDTO;
import com.example.mefitbackend.models.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO toProfileDto(Profile profile);
    Profile toProfile(ProfileDTO profileDto);
}
