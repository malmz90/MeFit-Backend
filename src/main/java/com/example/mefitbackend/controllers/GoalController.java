package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Exercise;
import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.services.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;
    private HttpStatus httpStatus;

    @Operation(summary = "Get all goals")
    @GetMapping("goals")
    public List<Goal> getGoals() {
        return goalService.getGoals();
    }

    @Operation(summary = "Get goal by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Goal.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Goal does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable int id) {
        HttpStatus status;
        Goal goal = goalService.findGoalById(id);

        if (goal != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(goal, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }

    }


    // NEEDS TO BE FIXED WITH DTO?!!!
    @Operation(summary = "Create a new goal")
    @PostMapping()
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            goal = goalService.saveGoal(goal);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(goal, httpStatus);
    }

    @Operation(summary = "Update an existing goal by ID")
    @PatchMapping("{id}")
    public ResponseEntity<Goal> updateGoal(@RequestBody Goal goal, @PathVariable int id) {
        if(id != goal.getGoal_id())
            return ResponseEntity.badRequest().build();
        goalService.updateGoal(goal);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete goal by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable int id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
