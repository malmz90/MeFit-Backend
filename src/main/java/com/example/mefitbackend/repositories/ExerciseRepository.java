package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {
}
