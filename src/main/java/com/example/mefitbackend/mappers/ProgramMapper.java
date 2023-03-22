package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.models.GoalProgramAssociation;
import com.example.mefitbackend.models.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {
    @Mapping(target = "program_id", source = "program.program_id")
    @Mapping(target = "name", source = "program.name")
    @Mapping(target = "category", source = "program.category")
    @Mapping(target = "completed", expression = "java(isProgramCompleted(program.getGoalProgramAssociations().get(0)))")
    ProgramDTO toProgramDto(Program program);

    @Mapping(target = "program_id", source = "program.program_id")
    @Mapping(target = "name", source = "program.name")
    @Mapping(target = "category", source = "program.category")
    @Mapping(target = "completed", source = "goalProgramAssociation.completed")
    ProgramDTO toProgramDtoWithAssociation(Program program, GoalProgramAssociation goalProgramAssociation);

    default boolean isProgramCompleted(GoalProgramAssociation goalProgramAssociation) {
        return goalProgramAssociation.isCompleted();
    }
}