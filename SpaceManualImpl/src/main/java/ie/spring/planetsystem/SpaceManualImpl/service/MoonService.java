package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;

import ie.spring.planetsystem.SpaceManualImpl.entities.Moon;

public interface MoonService {

    /** Get all moons. */
    List<Moon> getAllMoons();

    /** Get moon by ID. */
    Moon getMoonById(Long id);

    /** Get moons by planet name. */
    List<Moon> getMoonsByPlanetName(String planetName);

    /** Create moon (validates planet exists). */
    Moon createMoon(Moon moon);

    /** Update moon details. */
    Moon updateMoon(Long id, Moon moon);

    /** Delete moon by ID. */
    void deleteMoon(Long id);

    /** Count moons by planet name. */
    long countMoonsByPlanetName(String planetName);
}

