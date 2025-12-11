package ie.spring.planetsystem.SpaceManualImpl.graphql;

import ie.spring.planetsystem.SpaceManualImpl.dto.MyUserDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.UserCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequiredArgsConstructor
public class UserGraphqlController {

    private final MyUserService myUserService;

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public MyUserDTO userById(@Argument Long id) {
        return myUserService.getUserById(id);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MyUserDTO createUser(@Argument("input") UserCreateDTO input) {
        return myUserService.createUser(input);
    }
}