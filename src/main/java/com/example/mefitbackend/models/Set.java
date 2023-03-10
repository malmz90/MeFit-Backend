package com.example.mefitbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int set_id;
    @Column(name = "exercise_repetitions")
    private String exerciseRepetitions;

    @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "set_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    public List<Exercise> exercises = new ArrayList<>();
}
