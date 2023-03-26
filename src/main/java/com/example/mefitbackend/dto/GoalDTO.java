package com.example.mefitbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoalDTO {

    private Long goal_id;
    private String startDate;
    private String endDate;
    private boolean achieved;
    private List<ProgramDTO> programs;
    private Long profile_id;

    public Long getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(Long goal_id) {
        this.goal_id = goal_id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }


    public List<ProgramDTO> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramDTO> programs) {
        this.programs = programs;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }
}
