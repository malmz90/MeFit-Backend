package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.models.GoalProgramAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalProgramAssociationRepository   extends JpaRepository<GoalProgramAssociation, Integer> {
}
