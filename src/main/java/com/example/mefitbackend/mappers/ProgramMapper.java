package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.ProgramDTO;
import com.example.mefitbackend.models.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(target = "program_id", source = "program.program_id")
    @Mapping(target = "name", source = "program.name")
    @Mapping(target = "category", source = "program.category")
    ProgramDTO toProgramDto(Program program);
}
