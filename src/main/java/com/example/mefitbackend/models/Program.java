package com.example.mefitbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long program_id;

    @Column
    private String name;

    @Column
    private String category;


    @OneToMany(mappedBy = "program")
    @JsonIgnore
    List<Goal> goal;

    @ManyToMany
    @JoinTable(
            name= "program_workout",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    @JsonIgnore
    public List<Workout> workouts = new ArrayList<>();
}
