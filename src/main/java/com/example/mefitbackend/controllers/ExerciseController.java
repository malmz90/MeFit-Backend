package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.services.ExerciseService;
import com.example.mefitbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;
    private HttpStatus httpStatus;

    @Operation(summary = "Get all exercises")
    @GetMapping()
    public List<Exercise> getExercises() {
        return exerciseService.getExercises();
    }

    @Operation(summary = "Get exercise by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getUser(@PathVariable int id) {
        HttpStatus status;
        Exercise exercise = exerciseService.getExerciseById(id);

        if (exercise != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(exercise, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    @Operation(summary = "Create a new exercise")
    @PostMapping
    public ResponseEntity<Exercise> add(@RequestBody Exercise exercise) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            exercise = exerciseService.saveExercise(exercise);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(exercise, httpStatus);
    }

    @Operation(summary = "Update an existing exercise by ID")
    @PatchMapping("{id}")
    public ResponseEntity<Exercise> updateUser(@PathVariable int id, @RequestBody Exercise exercise) {

        if(id != exercise.getExercise_id()) {
            return ResponseEntity.badRequest().build();
        }
        exerciseService.updateExercise(exercise);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete exercise by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Exercise> delete(@PathVariable int id) {
        exerciseService.deleteExerciseById(id);
        return ResponseEntity.noContent().build();
    }

}
