package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ie.spring.planetsystem.SpaceManualImpl.exceptions.ConflictException;
import ie.spring.planetsystem.SpaceManualImpl.exceptions.NotFoundException;
import ie.spring.planetsystem.SpaceManualImpl.mappers.Mappers;
import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetCreateUpdateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetSummaryDTO;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MoonRepository;
import ie.spring.planetsystem.SpaceManualImpl.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PlanetDTO> findAll() {
        log.info("Fetching all planets");
        return planetRepository.findAll()
                .stream()
                .map(Mappers::mapPlanetToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlanetDTO findById(Long id) {
        log.info("Fetching planet by id: {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet with id " + id + " not found"));
        return Mappers.mapPlanetToDTO(planet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanetDTO> findByType(String type) {
        log.info("Fetching planets by type: {}", type);
        return planetRepository.findAllByTypeIgnoreCase(type)
                .stream()
                .map(Mappers::mapPlanetToDTO)
                .toList();
    }

    @Override
    @Transactional
    public PlanetDTO createPlanet(PlanetCreateUpdateDTO dto) {
        log.info("Creating planet with name: {}", dto.name());

        planetRepository.findByNameIgnoreCase(dto.name())
                .ifPresent(existing -> {
                    throw new ConflictException("Planet with name '" + dto.name() + "' already exists");
                });

        Planet planet = new Planet();
        planet.setName(dto.name());
        planet.setType(dto.type());
        planet.setRadiusKm(dto.radiusKm());
        planet.setMassKg(dto.massKg());
        planet.setOrbitalPeriodDays(dto.orbitalPeriodDays());

        Planet saved = planetRepository.save(planet);
        return Mappers.mapPlanetToDTO(saved);
    }

    @Override
    @Transactional
    public PlanetDTO updatePlanet(Long id, PlanetCreateUpdateDTO dto) {
        log.info("Updating planet id {} with payload: {}", id, dto);

        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet with id " + id + " not found"));

        planetRepository.findByNameIgnoreCase(dto.name())
                .filter(other -> !other.getPlanetId().equals(id))
                .ifPresent(other -> {
                    throw new ConflictException("Another planet with name '" + dto.name() + "' already exists");
                });

        planet.setName(dto.name());
        planet.setType(dto.type());
        planet.setRadiusKm(dto.radiusKm());
        planet.setMassKg(dto.massKg());
        planet.setOrbitalPeriodDays(dto.orbitalPeriodDays());

        Planet updated = planetRepository.save(planet);
        return Mappers.mapPlanetToDTO(updated);
    }

    @Override
    @Transactional
    public void deletePlanet(Long id) {
          log.info("Deleting planet with id: {}", id);

        if (!planetRepository.existsById(id)) {
            throw new NotFoundException("Planet with id " + id + " not found");
        }
        planetRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanetSummaryDTO getPlanetSummary(Long id) {
        log.info("Fetching planet summary for id {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet with id " + id + " not found"));
        return Mappers.mapPlanetToSummaryDTO(planet);
    }

    @Override
    @Transactional(readOnly = true)
    public long getMoonCountByPlanetId(Long planetId) {
        log.info("Counting moons for planet id: {}", planetId);
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new NotFoundException("Planet not found with id: " + planetId + " not found"));
        
        return moonRepository.countByPlanet_NameIgnoreCase(planet.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public long getMoonCountByPlanetName(String planetName) {
        log.info("Counting moons for planet name: {}", planetName);
        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new NotFoundException("Planet not found with name: " + planetName + " not found"));
        
        return moonRepository.countByPlanet_NameIgnoreCase(planetName);
    }
}

