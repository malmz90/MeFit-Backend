package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public List<Goal> getGoals() {
       return goalRepository.findAll();
    }

    public Goal findGoalById(Integer id) {
        return goalRepository.findById(id).orElse(null);
    }

    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal updateGoal(Goal goal) { return goalRepository.save(goal); }

    public void deleteById(Integer id) {
        if(goalRepository.existsById(id)) {
            Goal goal = goalRepository.findById(id).get();
            goalRepository.delete(goal);
        }
    }
}
