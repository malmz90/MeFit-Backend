package com.example.mefitbackend.services;

import com.example.mefitbackend.dto.GoalProgramAssociationUpdateDTO;
import com.example.mefitbackend.models.GoalProgramAssociation;
import com.example.mefitbackend.repositories.GoalProgramAssociationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalProgramAssociationService {

    @Autowired
    private GoalProgramAssociationRepository goalProgramAssociationRepository;

    public void updateCompleted(GoalProgramAssociationUpdateDTO updateDTO) {
        GoalProgramAssociation goalProgramAssociation = goalProgramAssociationRepository.findByGoalIdAndProgramId(
                updateDTO.getGoalId(),
                updateDTO.getProgramId()
        );

        if (goalProgramAssociation == null) {
            throw new EntityNotFoundException("GoalProgramAssociation not found");
        }

        goalProgramAssociation.setCompleted(updateDTO.isCompleted());
        goalProgramAssociationRepository.save(goalProgramAssociation);
    }
}
