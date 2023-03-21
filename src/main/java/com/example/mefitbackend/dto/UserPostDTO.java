package com.example.mefitbackend.dto;

import lombok.Data;

@Data
public class UserPostDTO {
    private String username;
    private String keyCloakId;
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


    public String getKeyCloakId() {
        return keyCloakId;
    }

    public void setKeyCloakId(String keyCloakId) {
        this.keyCloakId = keyCloakId;
    }
}
