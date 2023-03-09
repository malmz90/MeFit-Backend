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

    public List<Goal> getAll() {
       return goalRepository.findAll();
    }

    public Goal findById(Integer id) {
        return goalRepository.findById(id).orElse(null);
    }

    public Goal add(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal update(Goal goal) { return goalRepository.save(goal); }

    public void deleteById(Integer id) {
        if(goalRepository.existsById(id)) {
            Goal goal = goalRepository.findById(id).get();
            goalRepository.delete(goal);
        }
    }
}
