package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.GoalDTO;

import com.example.mefitbackend.models.Goal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(target = "program_id", source ="program.program_id")
    @Mapping(target ="profile_id", source="profile.profile_id")
    GoalDTO toGoalDto(Goal goal);

    @Mapping(target = "program.program_id", source ="program_id")
    @Mapping(target ="profile.profile_id", source="profile_id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "achieved", source = "achieved")
    Goal toGoal(GoalDTO goalDto);

}
