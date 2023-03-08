package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.models.Workout;
import com.example.mefitbackend.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;
    private HttpStatus httpStatus;

    @GetMapping()
    public List<Workout> getProfiles() {
        return workoutService.getWorkouts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getUser(@PathVariable int id) {
        HttpStatus status;
        Workout workout = workoutService.getWorkoutById(id);

        if (workout != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(workout, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    @PostMapping
    public ResponseEntity<Workout> add(@RequestBody Workout workout) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            workout = workoutService.saveWorkout(workout);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(workout, httpStatus);
    }

    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody Workout workout) {

        if(id != workout.getWorkout_id()) {
            return ResponseEntity.badRequest().build();
        }
       workoutService.updateWorkout(workout);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Workout> delete(@PathVariable int id) {
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
