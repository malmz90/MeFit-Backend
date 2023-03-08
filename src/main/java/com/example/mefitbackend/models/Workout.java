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
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workout_id;
    @Column
    private String name;
    @Column
    private String type;

    @ManyToMany
    @JoinTable(
            name = "program_workout",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "program_id")}
    )
    @JsonIgnore
    public List<Program> programs = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "workout_exercise",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    @JsonIgnore
    public List<Exercise> exercises = new ArrayList<>();
/* @OneToMany(mappedBy = "workout")
 List<WorkoutComplete> workoutsCompleted;*/
    
}
