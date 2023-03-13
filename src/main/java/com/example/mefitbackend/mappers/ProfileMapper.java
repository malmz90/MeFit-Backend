package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProfileDTO;
import com.example.mefitbackend.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "user",source ="user.user_id")
    @Mapping(target ="goals", source="goals")
    ProfileDTO toProfileDto(Profile profile);
 /*   Profile toProfile(ProfileDTO profileDto);*/
}
