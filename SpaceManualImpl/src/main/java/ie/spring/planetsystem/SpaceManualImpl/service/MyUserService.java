package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.Optional;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;

public interface MyUserService {

    /** Get user by ID (GraphQL). */
    MyUser getUserById(Long id);

    /** Create user (GraphQL, ADMIN only). */
    MyUser createUser(MyUser user);

    /** Find user by username (authentication). */
    Optional<MyUser> findByUsername(String username);
}

