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
    @Column
    private String password;
    @Column
    private Boolean isContributor;
    @Column
    private Boolean isAdmin;


}
