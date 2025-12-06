package com.example.space.mapper;

import com.example.space.domain.AppUser;
import com.example.space.dto.UserCreateDto;
import com.example.space.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(AppUser entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());
        return dto;
    }

    public static AppUser toEntity(UserCreateDto dto, String encodedPassword) {
        return AppUser.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .role(dto.getRole())
                .build();
    }
}
