package com.example.space.service;

import com.example.space.domain.AppUser;
import com.example.space.dto.UserCreateDto;
import com.example.space.dto.UserDto;
import com.example.space.exception.NotFoundException;
import com.example.space.mapper.UserMapper;
import com.example.space.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User " + id + " not found"));
        return UserMapper.toDto(user);
    }

    public UserDto create(UserCreateDto dto) {
        String encoded = passwordEncoder.encode(dto.getPassword());
        AppUser user = UserMapper.toEntity(dto, encoded);
        AppUser saved = userRepository.save(user);
        return UserMapper.toDto(saved);
    }
}
