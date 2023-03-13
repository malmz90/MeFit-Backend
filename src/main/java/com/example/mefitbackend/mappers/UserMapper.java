package com.example.mefitbackend.mappers;

import com.example.mefitbackend.dto.UserGetDTO;
import com.example.mefitbackend.dto.UserPostDTO;
import com.example.mefitbackend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserGetDTO userToUserGetDTO(User user);
    User userPostDTOtoUser(UserPostDTO userPostDTO);
}