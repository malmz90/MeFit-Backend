package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.GoalDTO;

import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.models.Goal;

import com.example.mefitbackend.models.GoalProgramAssociation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = ProgramMapper.class)
public interface GoalMapper {

    ProgramMapper programMapper = Mappers.getMapper(ProgramMapper.class);
    @Mapping(target = "goal_id", source = "goal.goal_id")
    @Mapping(target = "startDate", source = "goal.startDate")
    @Mapping(target = "endDate", source = "goal.endDate")
    @Mapping(target = "achieved", source = "goal.achieved")
    @Mapping(target = "programs", source = "goal.goalProgramAssociations")
    @Mapping(target = "profile_id", source = "goal.profile.profile_id")
    GoalDTO toGoalDto(Goal goal);

    @AfterMapping
    default void mapPrograms(@MappingTarget GoalDTO goalDto, Goal goal) {
        List<ProgramDTO> programDtos = new ArrayList<>();
        int totalPrograms = 0;
        int completedPrograms = 0;

        for (GoalProgramAssociation association : goal.getGoalProgramAssociations()) {
            ProgramDTO programDto = association.getProgram() != null ?
                    programMapper.toProgramDtoWithAssociation(association.getProgram(), association) : null;
            programDtos.add(programDto);

            if (programDto != null) {
                totalPrograms++;
                if (programDto.isCompleted()) {
                    completedPrograms++;
                }
            }
        }

        goalDto.setPrograms(programDtos);
        goalDto.setTotalPrograms(totalPrograms);
        goalDto.setCompletedPrograms(completedPrograms);
    }

    Goal toGoal(GoalDTO goalDTO);
}