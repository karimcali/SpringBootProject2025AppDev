package com.planets.api.graphql;

import com.planets.api.dto.UserCreateDTO;
import com.planets.api.dto.UserDTO;
import com.planets.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserGraphQLController {

    private final UserService userService;

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    public UserDTO userById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createUser(@Argument CreateUserInput input) {
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username(input.getUsername())
                .password(input.getPassword())
                .role(input.getRole())
                .build();
        return userService.createUser(userCreateDTO);
    }
}
