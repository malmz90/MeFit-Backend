package com.example.mefitbackend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;


/*    @OneToMany(mappedBy = "program")
    List<Goal> goal;*/

  /*  @ManyToMany
    @JoinTable(
            name= "program_workout",
            joinColumns = {@JoinColumn(name = "program_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")}
    )
    public List<Workout> workouts = new ArrayList<>();*/
}
