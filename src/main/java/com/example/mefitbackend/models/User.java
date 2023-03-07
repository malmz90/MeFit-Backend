package com.example.mefitbackend.models;

import jakarta.persistence.*;

@Entity
@Table(name="\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column private String username;
    @Column
    private String password;
    @Column
    private Boolean isContributor;
    @Column
    private Boolean isAdmin;


}
