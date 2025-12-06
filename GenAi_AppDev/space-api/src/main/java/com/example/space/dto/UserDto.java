package com.example.space.dto;

import com.example.space.domain.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private Role role;
}
