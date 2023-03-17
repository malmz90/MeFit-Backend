package com.example.mefitbackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ProfileGetDTO {

    private Long profile_id;

    private int weight;

    private int height;

    private String medicalConditions;

    private String disabilities;

    private int user;
    private List<Long> goals_id;


    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
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


    public List<Long> getGoals_id() {
        return goals_id;
    }

    public void setGoals_id(List<Long> goals_id) {
        this.goals_id = goals_id;
    }
}
