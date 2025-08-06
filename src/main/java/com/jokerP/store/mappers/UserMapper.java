package com.jokerP.store.mappers;

import com.jokerP.store.dtos.RegisterUserRequest;
import com.jokerP.store.dtos.UpdateUserRequest;
import com.jokerP.store.dtos.UserDto;
import com.jokerP.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest registerUserRequest);
    void update(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}
