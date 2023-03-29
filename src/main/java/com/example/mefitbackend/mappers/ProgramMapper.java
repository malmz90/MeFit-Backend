package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.models.GoalProgramAssociation;
import com.example.mefitbackend.models.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkoutMapper.class})
public interface ProgramMapper {
    @Mapping(target = "program_id", source = "program.program_id")
    @Mapping(target = "name", source = "program.name")
    @Mapping(target = "category", source = "program.category")
    @Mapping(target = "completed", expression = "java(isProgramCompleted(program.getGoalProgramAssociations()))")
    @Mapping(target = "workouts", source = "program.workouts")
    ProgramDTO toProgramDto(Program program);

    @Mapping(target = "program_id", source = "program.program_id")
    @Mapping(target = "name", source = "program.name")
    @Mapping(target = "category", source = "program.category")
    @Mapping(target = "completed", source = "goalProgramAssociation.completed")
    @Mapping(target = "workouts", source = "program.workouts")
    ProgramDTO toProgramDtoWithAssociation(Program program, GoalProgramAssociation goalProgramAssociation);

    default boolean isProgramCompleted(List<GoalProgramAssociation> goalProgramAssociations) {
        if (goalProgramAssociations == null || goalProgramAssociations.isEmpty()) {
            return false;
        }
        return goalProgramAssociations.get(0).isCompleted();
    }
}