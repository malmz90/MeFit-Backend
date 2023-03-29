package com.example.mefitbackend.controllers;

import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.dto.ProgramWithWorkoutsDTO;
import com.example.mefitbackend.mappers.ProgramMapper;
import com.example.mefitbackend.models.Profile;
import com.example.mefitbackend.models.Program;
import com.example.mefitbackend.models.User;
import com.example.mefitbackend.models.Workout;
import com.example.mefitbackend.services.ProgramService;
import com.example.mefitbackend.services.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("api/v1/programs")
public class ProgramController {
    @Autowired
    ProgramService programService;

    @Autowired
    ProgramMapper programMapper;
    private HttpStatus httpStatus;

    @Operation(summary = "Get all programs")
    @GetMapping()
    public List<ProgramDTO> getPrograms() {
        List<Program> programs = programService.getPrograms();
        return programs.stream().map(programMapper::toProgramDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get program by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Program.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Program does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgram(@PathVariable int id) {
        HttpStatus status;
        Program program = programService.findProgramById(id);

        if (program != null) {
            status = HttpStatus.OK;
            return new ResponseEntity<>(program, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    @Operation(summary = "Create a new program")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created program",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Program.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Not Authorized",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProgramDTO> add(@RequestBody ProgramWithWorkoutsDTO programWithWorkoutsDTO) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        Program program = new Program();
        program.setName(programWithWorkoutsDTO.getName());
        program.setCategory(programWithWorkoutsDTO.getCategory());

        try {
            program = programService.saveProgram(program);
            List<Integer> workoutIds = programWithWorkoutsDTO.getWorkoutIds();

            if (workoutIds != null && !workoutIds.isEmpty()) {
                program = programService.linkProgramWithWorkouts(program.getProgram_id(), workoutIds);
            }

            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        ProgramDTO programDTO = programMapper.toProgramDto(program);
        return new ResponseEntity<>(programDTO, httpStatus);
    }
 /*   @PostMapping
    public ResponseEntity<Program> add(@RequestBody Program program) {
        httpStatus = HttpStatus.FORBIDDEN;
        try {
            program = programService.saveProgram(program);
            httpStatus = HttpStatus.CREATED;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(program, httpStatus);
    }*/

    @Operation(summary = "Update an existing program by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Program successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Program not found with supplied ID",
                    content = @Content)
    })
    @PatchMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody Program program) {

        if(id != program.getProgram_id()) {
            return ResponseEntity.badRequest().build();
        }
        programService.updateProgram(program);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete program by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Workout> delete(@PathVariable int id) {
        programService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
