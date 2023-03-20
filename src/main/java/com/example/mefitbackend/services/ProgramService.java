package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Program;
import com.example.mefitbackend.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public Program findProgramById(int id) {
        Optional<Program> program = programRepository.findById(id);
        return program.orElse(null);
    }
    public List<Program> getPrograms(){
        return programRepository.findAll();
    };


    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public Program updateProgram(Program program) {
        return programRepository.save(program);
    }

    public void deleteById(Integer id) {
        programRepository.deleteById(id);
    }
}
