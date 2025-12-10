package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MoonRepository;
import ie.spring.planetsystem.SpaceManualImpl.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Planet> getAllPlanets() {
        log.info("Fetching all planets");
        return planetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Planet getPlanetById(Long id) {
        log.info("Fetching planet by id: {}", id);
        return planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Planet> getPlanetsByType(String type) {
        log.info("Fetching planets by type: {}", type);
        return planetRepository.findAllByTypeIgnoreCase(type);
    }

    @Override
    public Planet createPlanet(Planet planet) {
        log.info("Creating new planet: {}", planet.getName());
        Planet savedPlanet = planetRepository.save(planet);
        log.info("Planet created successfully with id: {}", savedPlanet.getPlanetId());
        return savedPlanet;
    }

    @Override
    public Planet updatePlanet(Long id, Planet planet) {
        log.info("Updating planet with id: {}", id);
        Planet existingPlanet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));
        
        Planet updatedPlanet = planetRepository.save(existingPlanet);
        log.info("Planet updated successfully: {}", id);
        return updatedPlanet;
    }

    @Override
    public void deletePlanet(Long id) {
        log.info("Deleting planet with id: {}", id);
        if (!planetRepository.existsById(id)) {
            throw new RuntimeException("Planet not found with id: " + id);
        }
        planetRepository.deleteById(id);
        log.info("Planet deleted successfully: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanetSummary getPlanetSummary(Long id) {
        log.info("Fetching planet summary (specific fields) for id: {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));
        
        return new PlanetSummary(
                planet.getPlanetId(),
                planet.getName(),
                planet.getMassKg()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public long getMoonCountByPlanetId(Long planetId) {
        log.info("Counting moons for planet id: {}", planetId);
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + planetId));
        
        return moonRepository.countByPlanet_NameIgnoreCase(planet.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public long getMoonCountByPlanetName(String planetName) {
        log.info("Counting moons for planet name: {}", planetName);
        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new RuntimeException("Planet not found with name: " + planetName));
        
        return moonRepository.countByPlanet_NameIgnoreCase(planetName);
    }
}

