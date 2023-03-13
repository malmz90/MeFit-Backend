package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProfileGetDTO;
import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "user", source ="user.user_id")
    @Mapping(target ="goals", source="goals", qualifiedByName = "goalsToIds")
    ProfileGetDTO toProfileDto(Profile profile);

    @Named("goalsToIds")
   default List<Long> map(List<Goal> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getGoal_id()).collect(Collectors.toList());
    }
}



