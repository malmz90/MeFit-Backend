package com.example.mefitbackend.services;

import com.example.mefitbackend.models.User;
import com.example.mefitbackend.models.Workout;
import com.example.mefitbackend.repositories.WorkoutRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    WorkoutRepository workoutRepository;

    public List<Workout> getWorkouts(){
        return workoutRepository.findAll();
    };

    public Workout getWorkoutById(int id) {return workoutRepository.findById(id).orElse(null); }

    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public void deleteById(Integer id) {
        workoutRepository.deleteById(id);
    }


}
