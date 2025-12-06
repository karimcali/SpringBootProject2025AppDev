package com.example.space.graphql;

import com.example.space.domain.Role;
import com.example.space.dto.UserCreateDto;
import com.example.space.dto.UserDto;
import com.example.space.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserGraphqlController {

    private final UserService userService;

    @QueryMapping
    public UserDto userById(@Argument("id") Long id) {
        return userService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto createUser(@Argument("input") CreateUserInput input) {
        UserCreateDto dto = new UserCreateDto();
        dto.setUsername(input.username());
        dto.setPassword(input.password());
        dto.setRole(Role.valueOf(input.role()));
        return userService.create(dto);
    }

    public record CreateUserInput(String username, String password, String role) {}
}
