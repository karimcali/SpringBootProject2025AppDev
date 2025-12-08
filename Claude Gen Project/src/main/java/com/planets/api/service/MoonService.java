package com.planets.api.service;

import com.planets.api.dto.MoonDTO;
import com.planets.api.dto.MoonMapper;
import com.planets.api.entity.Moon;
import com.planets.api.entity.Planet;
import com.planets.api.exception.ResourceNotFoundException;
import com.planets.api.repository.MoonRepository;
import com.planets.api.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MoonService {

    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;
    private final MoonMapper moonMapper;

    public List<MoonDTO> getAllMoons() {
        log.info("Fetching all moons");
        return moonRepository.findAll().stream()
                .map(moonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MoonDTO getMoonById(Long id) {
        log.info("Fetching moon by id: {}", id);
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moon", "id", id));
        return moonMapper.toDTO(moon);
    }

    public List<MoonDTO> getMoonsByPlanetId(Long planetId) {
        log.info("Fetching moons by planet id: {}", planetId);
        if (!planetRepository.existsById(planetId)) {
            throw new ResourceNotFoundException("Planet", "id", planetId);
        }
        return moonRepository.findByPlanetId(planetId).stream()
                .map(moonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MoonDTO createMoon(MoonDTO moonDTO) {
        log.info("Creating new moon: {}", moonDTO.getName());

        Planet planet = planetRepository.findById(moonDTO.getPlanetId())
                .orElseThrow(() -> new ResourceNotFoundException("Planet", "id", moonDTO.getPlanetId()));

        Moon moon = moonMapper.toEntity(moonDTO);
        moon.setPlanet(planet);

        Moon savedMoon = moonRepository.save(moon);
        log.info("Moon created successfully: {}", savedMoon.getId());
        return moonMapper.toDTO(savedMoon);
    }

    public MoonDTO updateMoon(Long id, MoonDTO moonDTO) {
        log.info("Updating moon with id: {}", id);
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moon", "id", id));

        if (moonDTO.getPlanetId() != null && !moonDTO.getPlanetId().equals(moon.getPlanet().getId())) {
            Planet planet = planetRepository.findById(moonDTO.getPlanetId())
                    .orElseThrow(() -> new ResourceNotFoundException("Planet", "id", moonDTO.getPlanetId()));
            moon.setPlanet(planet);
        }

        moonMapper.updateEntity(moonDTO, moon);
        Moon updatedMoon = moonRepository.save(moon);
        log.info("Moon updated successfully: {}", id);
        return moonMapper.toDTO(updatedMoon);
    }

    public void deleteMoon(Long id) {
        log.info("Deleting moon with id: {}", id);
        if (!moonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Moon", "id", id);
        }
        moonRepository.deleteById(id);
        log.info("Moon deleted successfully: {}", id);
    }
}
