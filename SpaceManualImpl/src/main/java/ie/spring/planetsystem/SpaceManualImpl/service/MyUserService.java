package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.Optional;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import ie.spring.planetsystem.SpaceManualImpl.dto.UserCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.MyUserDTO;

public interface MyUserService {

    /** Get user by ID (GraphQL). */
    MyUserDTO getUserById(Long id);

    /** Create user (GraphQL, ADMIN only). */
    MyUserDTO createUser(UserCreateDTO dto);

    /** Optional: get user as DTO by username (for APIs). */
    MyUserDTO getUserByUsername(String username);

    /** Internal use (security): entity-based lookup. */
    Optional<MyUser> findByUsername(String username);
}

