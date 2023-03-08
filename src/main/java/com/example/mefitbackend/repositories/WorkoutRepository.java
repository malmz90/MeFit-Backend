package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {
}
