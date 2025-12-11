package ie.spring.planetsystem.SpaceManualImpl.service;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ie.spring.planetsystem.SpaceManualImpl.exceptions.ConflictException;
import ie.spring.planetsystem.SpaceManualImpl.exceptions.NotFoundException;
import ie.spring.planetsystem.SpaceManualImpl.mappers.Mappers;
import ie.spring.planetsystem.SpaceManualImpl.dto.UserCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.MyUserDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public MyUserDTO getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
       MyUser user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        return Mappers.mapMyUserToMyUserDTO(user);
    }

    @Override
    public MyUserDTO createUser(UserCreateDTO dto) {
        log.info("Creating new user: {}", dto.getUsername());
         // Ensure username is unique
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(existing -> {
                    throw new ConflictException(
                            "User with username '" + dto.getUsername() + "' already exists"
                    );
                });

        MyUser user = new MyUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setEnabled(true);
        user.setUnlocked(true);

        MyUser savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getUserId());
        return Mappers.mapMyUserToMyUserDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public MyUserDTO getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        MyUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + username));
        return Mappers.mapMyUserToMyUserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MyUser> findByUsername(String username) {
        // This is mainly for Spring Security (UserDetailsService)
        log.debug("Finding user by username for security: {}", username);
        return userRepository.findByUsername(username);
    }
}

