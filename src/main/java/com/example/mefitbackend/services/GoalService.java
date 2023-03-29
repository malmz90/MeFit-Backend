package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Goal updateGoal(Goal goal) {
        Optional<Goal> existingGoalOptional = goalRepository.findById(Math.toIntExact(goal.getGoal_id()));

        if (existingGoalOptional.isPresent()) {
            Goal existingGoal = existingGoalOptional.get();

            if (goal.isAchieved() != existingGoal.isAchieved()) {
                existingGoal.setAchieved(goal.isAchieved());
            }

            // Add other fields that you want to update if necessary

            return goalRepository.save(existingGoal);
        } else {
            throw new RuntimeException("Goal not found");
        }
    }

    public void deleteById(Integer id) {
        if(goalRepository.existsById(id)) {
            Goal goal = goalRepository.findById(id).get();
            goalRepository.delete(goal);
        }
    }
}
