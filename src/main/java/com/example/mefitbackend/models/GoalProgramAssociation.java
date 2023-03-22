package com.example.mefitbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GoalProgramAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "completed")
    private boolean completed;
}
