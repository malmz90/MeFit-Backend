package com.example.mefitbackend.dto;

import lombok.Data;

import java.util.List;

@Data
    public class WorkoutWithExercisesDTO {
        private String name;
        private String type;
        private List<Integer> exerciseIds;


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

    public List<Integer> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Integer> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }
}

