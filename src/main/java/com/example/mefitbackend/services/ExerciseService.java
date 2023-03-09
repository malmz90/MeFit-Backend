package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;


    public List<Exercise> getExercises(){
        return exerciseRepository.findAll();
    };

    public Exercise getExerciseById(int id) {return exerciseRepository.findById(id).orElse(null); }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExerciseById(Integer id) {
        exerciseRepository.deleteById(id);
    }
}
