package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.GoalDTO;
import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.mappers.GoalMapper;
import com.example.mefitbackend.models.Goal;
import com.example.mefitbackend.models.GoalProgramAssociation;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.Program;
import com.example.mefitbackend.repositories.GoalProgramAssociationRepository;
import com.example.mefitbackend.services.GoalService;
import com.example.mefitbackend.services.ProfileService;
import com.example.mefitbackend.services.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("api/v1/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;
    @Autowired
    private GoalMapper goalMapper;

    @Autowired
    private ProgramService programService;
    @Autowired
    ProfileService profileService;
    @Autowired
    GoalProgramAssociationRepository goalProgramAssociationRepository;
    private HttpStatus httpStatus;

    @Operation(summary = "Get all goals")
    @GetMapping()
    public List<Goal> getGoals() {
        return goalService.getGoals();
    }

    @Operation(summary = "Get goal by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Goal.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Goal does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<GoalDTO> getGoalById(@PathVariable int id) {
        HttpStatus status;
        Goal goal = goalService.findGoalById(id);

        if (goal != null) {
            status = HttpStatus.OK;
            GoalDTO goalDto = goalMapper.toGoalDto(goal);
            return new ResponseEntity<>(goalDto, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<GoalDTO>> getGoalsByIds(@RequestParam("ids") List<Integer> ids) {
        List<GoalDTO> goalDTOs = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;

        for (int id : ids) {
            Goal goal = goalService.findGoalById(id);
            if (goal != null) {
                GoalDTO goalDto = goalMapper.toGoalDto(goal);
                goalDTOs.add(goalDto);
            }
        }

        if (goalDTOs.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(goalDTOs, status);
    }


    // NEEDS TO BE FIXED WITH DTO?!!!
    @Operation(summary = "Create a new goal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created goal",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Goal.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Not Authorized",
                    content = @Content)
    })
    @PostMapping()
    public ResponseEntity<GoalDTO> addGoal(@RequestBody GoalDTO goalDto) {
        HttpStatus status;
        Goal goal = goalMapper.toGoal(goalDto);

        Profile profile = profileService.getProfileById(Math.toIntExact(goalDto.getProfile_id()));
        if (profile == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(null, status);
        }

        goal.setProfile(profile);

        List<GoalProgramAssociation> associations = new ArrayList<>();

        for (ProgramDTO programDto : goalDto.getPrograms()) {
            Program program = programService.findProgramById(Math.toIntExact(programDto.getProgram_id()));
            if (program == null) {
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(null, status);
            }
            GoalProgramAssociation association = new GoalProgramAssociation();
            association.setProgram(program);
            association.setGoal(goal);
            association.setCompleted(programDto.isCompleted());
            associations.add(association);
        }

        goal.setGoalProgramAssociations(associations);
        goal = goalService.saveGoal(goal);

        if (goal != null) {
            // Save the associations to the goal_program_association table
            for (GoalProgramAssociation association : associations) {
                association = goalProgramAssociationRepository.save(association);
            }

            status = HttpStatus.CREATED;
            GoalDTO responseDto = goalMapper.toGoalDto(goal);
            return new ResponseEntity<>(responseDto, status);
        } else {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(null, status);
        }
    }

    @Operation(summary = "Update an existing goal by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Goal successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Goal not found with supplied ID",
                    content = @Content)
    })
    @PatchMapping("{id}")
    public ResponseEntity<Goal> updateGoal(@RequestBody Goal goal, @PathVariable int id) {
        if(id != goal.getGoal_id())
            return ResponseEntity.badRequest().build();
        goalService.updateGoal(goal);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete goal by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable int id) {
        goalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
