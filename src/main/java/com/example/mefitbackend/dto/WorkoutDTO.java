package com.example.mefitbackend.dto;

import lombok.Data;

import java.util.List;
@Data
public class WorkoutDTO {
    private int workout_id;
    private String name;
    private String type;
    private List<ExerciseDTO> exercises;

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    // Getters and setters
}
