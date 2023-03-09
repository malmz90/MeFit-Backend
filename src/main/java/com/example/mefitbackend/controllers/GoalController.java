package com.example.mefitbackend.controllers;

import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping("goals")
    public List<Goal> getGoals() {
        return goalService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable int id) {
        return ResponseEntity.ok(goalService.findById(id));
    }


    // NEEDS TO BE FIXED!!!
    @PostMapping()
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) {
        Goal addGoal = goalService.add(goal);
        URI location = URI.create("goal/" + addGoal.getGoal_id());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Goal> updateGoal(@RequestBody Goal goal, @PathVariable int id) {
        if(id != goal.getGoal_id())
            return ResponseEntity.badRequest().build();
        goalService.update(goal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable int id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}