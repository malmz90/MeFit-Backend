package com.example.mefitbackend.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProgramWithWorkoutsDTO {
    private String name;
    private String category;
    private List<Integer> workoutIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getWorkoutIds() {
        return workoutIds;
    }

    public void setWorkoutIds(List<Integer> workoutIds) {
        this.workoutIds = workoutIds;
    }
}
