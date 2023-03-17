package com.example.mefitbackend.dto;

import lombok.Data;

@Data
public class UserPostDTO {
    private String username;
    private String keycloakId;
    private Boolean isContributor = false;
    private Boolean isAdmin = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getContributor() {
        return isContributor;
    }

    public void setContributor(Boolean contributor) {
        isContributor = contributor;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }
}
