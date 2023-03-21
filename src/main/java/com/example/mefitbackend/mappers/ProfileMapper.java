package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.GoalDTO;
import com.example.mefitbackend.dto.ProfileGetDTO;
import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = GoalMapper.class)
public interface ProfileMapper {
    @Mapping(target = "user", source = "user.user_id")
    ProfileGetDTO toProfileDto(Profile profile);

    List<GoalDTO> toGoalDtoList(List<Goal> goals);

    ProgramDTO toProgramDto(Program program);
}



