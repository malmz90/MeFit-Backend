package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
}
