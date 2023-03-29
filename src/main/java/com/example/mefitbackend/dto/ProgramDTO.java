package com.example.mefitbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProgramDTO {
    private Long program_id;
    private String name;
    private String category;
    private boolean completed;
    private List<WorkoutDTO> workouts;


    public Long getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Long program_id) {
        this.program_id = program_id;
    }

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
