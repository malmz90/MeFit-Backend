package com.example.mefitbackend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "upper_body")
    private String upperBody;

    @Column(name = "lower_body")
    private String lowerBody;

   @ManyToMany
    @JoinTable(
            name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name= "workout_id")}
    )
    public List<Workout> workouts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "set_id")}
    )
    public List<Set> sets = new ArrayList<>();


}
