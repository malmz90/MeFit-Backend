package com.example.mefitbackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ProfileGetDTO {

    private int profile_id;
    private Integer weight;
    private Integer height;
    private String medicalConditions;
    private String disabilities;
    private int user;
    private List<GoalDTO> goals;
    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public String getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(String disabilities) {
        this.disabilities = disabilities;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public List<GoalDTO> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalDTO> goals) {
        this.goals = goals;
    }
}
