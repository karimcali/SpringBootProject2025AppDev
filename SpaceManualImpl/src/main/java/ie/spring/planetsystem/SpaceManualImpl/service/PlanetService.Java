package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetCreateUpdateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetSummaryDTO;

public interface PlanetService {
    // READ

    /** Get all planets. */
    List<PlanetDTO> findAll();

    /** Get planet by ID. */
    PlanetDTO findById(Long id);

    /** Get planets by type. */
    List<PlanetDTO> findByType(String type);

    /** Get planet summary. */
    PlanetSummaryDTO getPlanetSummary(Long id);

    /** Count moons by planet ID. */
    long getMoonCountByPlanetId(Long planetId);

    /** Count moons by planet name. */
    long getMoonCountByPlanetName(String planetName);

    // WRITE

    /** Create a new planet. */
    PlanetDTO createPlanet(PlanetCreateUpdateDTO dto);

    /** Update planet details. */
    PlanetDTO updatePlanet(Long id, PlanetCreateUpdateDTO dto);

    /** Delete planet by ID. */
    void deletePlanet(Long id);

    
}

