package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.models.Workout;
import com.example.mefitbackend.repositories.ExerciseRepository;
import com.example.mefitbackend.repositories.WorkoutRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Workout> getWorkouts(){
        return workoutRepository.findAll();
    };

    public Workout getWorkoutById(int id) {return workoutRepository.findById(id).orElse(null); }

    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Workout linkWorkoutWithExercises(int workoutId, List<Integer> exerciseIds) {
        Workout workout = workoutRepository.findById(workoutId).orElse(null);

        for (Integer exerciseId : exerciseIds) {
            Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
            workout.getExercises().add(exercise);
            exercise.getWorkouts().add(workout);
        }

        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public void deleteById(Integer id) {
        workoutRepository.deleteById(id);
    }


}
