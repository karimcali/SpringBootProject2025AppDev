package ie.spring.planetsystem.SpaceManualImpl.service;

import java.util.List;

import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;

public interface PlanetService {

    /** Get all planets. */
    List<Planet> getAllPlanets();

    /** Get planet by ID. */
    Planet getPlanetById(Long id);

    /** Get planets by type. */
    List<Planet> getPlanetsByType(String type);

    /** Create a new planet. */
    Planet createPlanet(Planet planet);

    /** Update planet details. */
    Planet updatePlanet(Long id, Planet planet);

    /** Delete planet by ID. */
    void deletePlanet(Long id);

    /** Get planet summary (name and mass only). */
    PlanetSummary getPlanetSummary(Long id);

    /** Count moons by planet ID. */
    long getMoonCountByPlanetId(Long planetId);

    /** Count moons by planet name. */
    long getMoonCountByPlanetName(String planetName);

    /** Planet summary DTO. */
    record PlanetSummary(Long planetId, String name, Double massKg) {}
}

