package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.GoalDTO;

import com.example.mefitbackend.models.Goal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    GoalDTO toGoalDto(Goal goal);
    Goal toGoal(GoalDTO goalDTO);
}
