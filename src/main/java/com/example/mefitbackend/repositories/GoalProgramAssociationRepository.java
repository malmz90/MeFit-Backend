package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.models.GoalProgramAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalProgramAssociationRepository extends JpaRepository<GoalProgramAssociation, Long> {

    @Query("SELECT gpa FROM GoalProgramAssociation gpa WHERE gpa.goal.goal_id = :goalId AND gpa.program.program_id = :programId")
    GoalProgramAssociation findByGoalIdAndProgramId(@Param("goalId") Long goalId, @Param("programId") Long programId);
}
