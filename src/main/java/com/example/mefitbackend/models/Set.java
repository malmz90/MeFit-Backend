package com.example.mefitbackend.models;

import jakarta.persistence.*;

@Entity
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "exercise_repetitions")
    private String exerciseRepetitions;

 /*   @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "set_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    public List<Exercise> exercises = new ArrayList<>();*/
}
