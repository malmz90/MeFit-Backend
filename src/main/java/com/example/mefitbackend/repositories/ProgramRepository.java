package com.example.mefitbackend.repositories;

import com.example.mefitbackend.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
