package com.example.mefitbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int weight;
    @Column
    private int height;

    @Column
    private String medicalConditions;
    @Column
    private String disabilities;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

 /*   @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;*/


   @OneToMany(mappedBy = "profile")
   List<Goal> goals;



}
