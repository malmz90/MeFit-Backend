package com.example.mefitbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "achieved")
    private boolean achieved;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

  /*  @OneToMany(mappedBy = "workout")
    List<WorkoutComplete> workoutsCompleted;
    */

}
