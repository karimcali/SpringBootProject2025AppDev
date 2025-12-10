package ie.spring.planetsystem.SpaceManualImpl.service;

import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public MyUser getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public MyUser createUser(MyUser user) {
        log.info("Creating new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        MyUser savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getUserId());
        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MyUser> findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        return userRepository.findByUsername(username);
    }
}

