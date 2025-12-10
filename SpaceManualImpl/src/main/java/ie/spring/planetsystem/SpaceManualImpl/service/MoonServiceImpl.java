package ie.spring.planetsystem.SpaceManualImpl.service;

import ie.spring.planetsystem.SpaceManualImpl.entities.Moon;
import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MoonRepository;
import ie.spring.planetsystem.SpaceManualImpl.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MoonServiceImpl implements MoonService {

    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Moon> getAllMoons() {
        log.info("Fetching all moons");
        return moonRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Moon getMoonById(Long id) {
        log.info("Fetching moon by id: {}", id);
        return moonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moon not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Moon> getMoonsByPlanetName(String planetName) {
        log.info("Fetching moons by planet name: {}", planetName);
        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new RuntimeException("Planet not found with name: " + planetName));
        
        return moonRepository.findAllByPlanet_NameIgnoreCase(planetName);
    }

    @Override
    public Moon createMoon(Moon moon) {
        log.info("Creating new moon: {}", moon.getName());
        
        Planet planet = moon.getPlanet();
        if (planet == null || planet.getPlanetId() == null) {
            throw new RuntimeException("Planet must be specified when creating a moon");
        }
        
        Planet existingPlanet = planetRepository.findById(planet.getPlanetId())
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + planet.getPlanetId()));
        
        moon.setPlanet(existingPlanet);
        
        Moon savedMoon = moonRepository.save(moon);
        log.info("Moon created successfully with id: {}", savedMoon.getMoonId());
        return savedMoon;
    }

    @Override
    public Moon updateMoon(Long id, Moon moon) {
        log.info("Updating moon with id: {}", id);
        Moon existingMoon = moonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moon not found with id: " + id));
        
        Moon updatedMoon = moonRepository.save(existingMoon);
        log.info("Moon updated successfully: {}", id);
        return updatedMoon;
    }

    @Override
    public void deleteMoon(Long id) {
        log.info("Deleting moon with id: {}", id);
        if (!moonRepository.existsById(id)) {
            throw new RuntimeException("Moon not found with id: " + id);
        }
        moonRepository.deleteById(id);
        log.info("Moon deleted successfully: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countMoonsByPlanetName(String planetName) {
        log.info("Counting moons for planet name: {}", planetName);
        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new RuntimeException("Planet not found with name: " + planetName));
        
        return moonRepository.countByPlanet_NameIgnoreCase(planetName);
    }
}

