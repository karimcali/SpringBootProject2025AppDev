package ie.spring.planetsystem.SpaceManualImpl.service;

import ie.spring.planetsystem.SpaceManualImpl.entities.Moon;
import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import ie.spring.planetsystem.SpaceManualImpl.repositories.MoonRepository;
import ie.spring.planetsystem.SpaceManualImpl.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ie.spring.planetsystem.SpaceManualImpl.exceptions.NotFoundException;
import ie.spring.planetsystem.SpaceManualImpl.mappers.Mappers;
import ie.spring.planetsystem.SpaceManualImpl.dto.MoonCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.MoonDTO;

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
    public List<MoonDTO> findAll() {
        log.info("Fetching all moons");
        return moonRepository.findAll()
                .stream()
                .map(Mappers::mapMoonToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MoonDTO findById(Long id) {
        log.info("Fetching moon by id: {}", id);
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Moon with id " + id + " not found"));
        return Mappers.mapMoonToDTO(moon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MoonDTO> findByPlanetName(String planetName) {
        log.info("Fetching moons by planet name: {}", planetName);

        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new NotFoundException("Planet with name '" + planetName + "' not found"));

        return moonRepository.findAllByPlanet_NameIgnoreCase(planetName)
                .stream()
                .map(Mappers::mapMoonToDTO)
                .toList();
    }

    @Override
    public MoonDTO createMoon(MoonCreateDTO dto) {
        log.info("Creating new moon: {} for planet id {}", dto.name(), dto.planetId());

        Planet planet = planetRepository.findById(dto.planetId())
                .orElseThrow(() -> new NotFoundException("Planet with id " + dto.planetId() + " not found"));

        Moon moon = new Moon();
        moon.setName(dto.name());
        moon.setDiameterKm(dto.diameterKm());
        moon.setOrbitalPeriodDays(dto.orbitalPeriodDays());
        moon.setPlanet(planet);

        Moon savedMoon = moonRepository.save(moon);
        log.info("Moon created successfully with id: {}", savedMoon.getMoonId());
        return Mappers.mapMoonToDTO(savedMoon);
    }

    @Override
    public MoonDTO updateMoon(Long id, MoonCreateDTO dto) {
        log.info("Updating moon with id: {}", id);

        Moon existingMoon = moonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Moon with id " + id + " not found"));

        // If planet id changed, re-associate
        if (!existingMoon.getPlanet().getPlanetId().equals(dto.planetId())) {
            Planet planet = planetRepository.findById(dto.planetId())
                    .orElseThrow(() -> new NotFoundException("Planet with id " + dto.planetId() + " not found"));
            existingMoon.setPlanet(planet);
        }

        existingMoon.setName(dto.name());
        existingMoon.setDiameterKm(dto.diameterKm());
        existingMoon.setOrbitalPeriodDays(dto.orbitalPeriodDays());

        Moon updatedMoon = moonRepository.save(existingMoon);
        log.info("Moon updated successfully: {}", id);
        return Mappers.mapMoonToDTO(updatedMoon);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting moon with id: {}", id);
        if (!moonRepository.existsById(id)) {
            throw new NotFoundException("Moon with id " + id + " not found");
        }
        moonRepository.deleteById(id);
        log.info("Moon deleted successfully: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByPlanetName(String planetName) {
        log.info("Counting moons for planet name: {}", planetName);

        planetRepository.findByNameIgnoreCase(planetName)
                .orElseThrow(() -> new NotFoundException("Planet with name '" + planetName + "' not found"));

        return moonRepository.countByPlanet_NameIgnoreCase(planetName);
    }
}

