package com.example.mefitbackend.services;

import com.example.mefitbackend.models.Program;
import com.example.mefitbackend.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public Program findProgramById(int id) {
        Optional<Program> program = programRepository.findById(id);
        return program.orElse(null);
    }
}
