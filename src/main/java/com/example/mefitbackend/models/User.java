package com.example.mefitbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column private String username;

    @Column(name = "keycloak_id",unique=true)
    private String keyCloakId;
    @Column(name = "is_contributor")
    private Boolean contributor;

    @Column(name = "is_admin")
    private Boolean admin;


}
