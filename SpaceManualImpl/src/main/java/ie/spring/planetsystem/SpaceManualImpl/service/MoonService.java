package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;

import ie.spring.planetsystem.SpaceManualImpl.dto.MoonCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.MoonDTO;

public interface MoonService {
    // READ

    /** Get all moons. */
    List<MoonDTO> findAll();

    /** Get moon by ID. */
    MoonDTO findById(Long id);

    /** Get moons by planet name. */
    List<MoonDTO> findByPlanetName(String planetName);

    /** Count moons by planet name. */
    long countByPlanetName(String planetName);

    // WRITE

    /** Create moon (validates planet exists). */
    MoonDTO createMoon(MoonCreateDTO dto);

    /** Update moon details. */
    MoonDTO updateMoon(Long id, MoonCreateDTO dto);

    /** Delete moon by ID. */
    void deleteById(Long id);

    
}

