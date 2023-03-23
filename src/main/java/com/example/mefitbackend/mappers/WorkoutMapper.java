package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ExerciseDTO;
import com.example.mefitbackend.dto.WorkoutDTO;
import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.Workout;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDTO toDTO(Workout workout);

    ExerciseDTO exerciseToDTO(Exercise exercise);

    default List<ExerciseDTO> mapExercises(Set<Exercise> exercises) {
        return exercises.stream()
                .map(this::exerciseToDTO)
                .collect(Collectors.toList());
    }
}
