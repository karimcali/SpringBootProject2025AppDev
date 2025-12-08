package com.planets.api.service;

import com.planets.api.dto.MoonCountDTO;
import com.planets.api.dto.PlanetDTO;
import com.planets.api.dto.PlanetMapper;
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
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;
    private final PlanetMapper planetMapper;

    public List<PlanetDTO> getAllPlanets() {
        log.info("Fetching all planets");
        return planetRepository.findAll().stream()
                .map(planetMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlanetDTO getPlanetById(Long id) {
        log.info("Fetching planet by id: {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", "id", id));
        return planetMapper.toDTO(planet);
    }

    public List<PlanetDTO> getPlanetsByType(String type) {
        log.info("Fetching planets by type: {}", type);
        return planetRepository.findByType(type).stream()
                .map(planetMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlanetDTO createPlanet(PlanetDTO planetDTO) {
        log.info("Creating new planet: {}", planetDTO.getName());
        Planet planet = planetMapper.toEntity(planetDTO);
        Planet savedPlanet = planetRepository.save(planet);
        log.info("Planet created successfully: {}", savedPlanet.getId());
        return planetMapper.toDTO(savedPlanet);
    }

    public PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO) {
        log.info("Updating planet with id: {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", "id", id));

        planetMapper.updateEntity(planetDTO, planet);
        Planet updatedPlanet = planetRepository.save(planet);
        log.info("Planet updated successfully: {}", id);
        return planetMapper.toDTO(updatedPlanet);
    }

    public void deletePlanet(Long id) {
        log.info("Deleting planet with id: {}", id);
        if (!planetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Planet", "id", id);
        }
        planetRepository.deleteById(id);
        log.info("Planet deleted successfully: {}", id);
    }

    public MoonCountDTO getMoonCountByPlanetId(Long planetId) {
        log.info("Counting moons for planet: {}", planetId);
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", "id", planetId));

        Long count = moonRepository.countByPlanetId(planetId);

        return MoonCountDTO.builder()
                .planetId(planet.getId())
                .planetName(planet.getName())
                .moonCount(count)
                .build();
    }
}
