package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.GoalProgramAssociationUpdateDTO;
import com.example.mefitbackend.services.GoalProgramAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/v1/goal-program-associations")
public class GoalProgramAssociationController {

    @Autowired
    private GoalProgramAssociationService goalProgramAssociationService;

    @PutMapping()
    public ResponseEntity<Void> updateCompleted(@RequestBody GoalProgramAssociationUpdateDTO updateDTO) {
        goalProgramAssociationService.updateCompleted(updateDTO);
        return ResponseEntity.ok().build();
    }
}
