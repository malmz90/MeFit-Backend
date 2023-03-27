package com.example.mefitbackend.dto;

import lombok.Data;

@Data
public class GoalProgramAssociationUpdateDTO {
    private Long goalId;
    private Long programId;
    private boolean completed;

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
