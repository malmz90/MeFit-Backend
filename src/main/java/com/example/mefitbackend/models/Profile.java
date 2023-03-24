package com.example.mefitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profile_id;

    @Column
    private Integer weight;
    @Column
    private Integer height;

    @Column
    private String medicalConditions;
    @Column
    private String disabilities;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

   @OneToMany(mappedBy = "profile")
   List<Goal> goals;


}
