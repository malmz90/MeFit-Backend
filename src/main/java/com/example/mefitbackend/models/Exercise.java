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
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;

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
   @JsonIgnore
    public List<Workout> workouts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "exercise_set",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "set_id")}
    )
    @JsonIgnore
    public List<Set> sets = new ArrayList<>();


}
