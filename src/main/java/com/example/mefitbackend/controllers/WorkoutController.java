package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.WorkoutDTO;
import com.example.mefitbackend.dto.WorkoutWithExercisesDTO;
import com.example.mefitbackend.mappers.WorkoutMapper;
import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.models.Workout;
import com.example.mefitbackend.services.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    @Autowired
    WorkoutService workoutService;

    @Autowired
    WorkoutMapper workoutMapper;
    private HttpStatus httpStatus;

    @Operation(summary = "Get all workouts")
    @GetMapping()
    public List<Workout> getProfiles() {
        return workoutService.getWorkouts();
    }

    @Operation(summary = "Get workout by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Workout.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Workout does not exist with supplied ID",
                    content = @Content)
    })
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

  /*  @Operation(summary = "Create a new workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created workout",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Not Authorized",
                    content = @Content)
    })
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
    }*/

    @PostMapping
    public ResponseEntity<WorkoutDTO> add(@RequestBody WorkoutWithExercisesDTO workoutWithExercisesDTO) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        Workout workout = new Workout();
        workout.setName(workoutWithExercisesDTO.getName());
        workout.setType(workoutWithExercisesDTO.getType());

        try {
            workout = workoutService.saveWorkout(workout);
            List<Integer> exerciseIds = workoutWithExercisesDTO.getExerciseIds();

            if (exerciseIds != null && !exerciseIds.isEmpty()) {
                workout = workoutService.linkWorkoutWithExercises(workout.getWorkout_id(), exerciseIds);
            }

            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        WorkoutDTO workoutDTO = workoutMapper.toDTO(workout);
        return new ResponseEntity<>(workoutDTO, httpStatus);
    }

    @Operation(summary = "Update an existing workout by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Workout successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Workout not found with supplied ID",
                    content = @Content)
    })
    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody Workout workout) {

        if(id != workout.getWorkout_id()) {
            return ResponseEntity.badRequest().build();
        }
       workoutService.updateWorkout(workout);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete workout by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Workout> delete(@PathVariable int id) {
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
