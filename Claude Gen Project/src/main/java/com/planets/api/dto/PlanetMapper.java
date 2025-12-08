package com.planets.api.dto;

import com.planets.api.entity.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper {

    public PlanetDTO toDTO(Planet planet) {
        if (planet == null) {
            return null;
        }
        return PlanetDTO.builder()
                .id(planet.getId())
                .name(planet.getName())
                .type(planet.getType())
                .radiusKm(planet.getRadiusKm())
                .massKg(planet.getMassKg())
                .orbitalPeriodDays(planet.getOrbitalPeriodDays())
                .moonCount(planet.getMoons() != null ? planet.getMoons().size() : 0)
                .build();
    }

    public Planet toEntity(PlanetDTO dto) {
        if (dto == null) {
            return null;
        }
        return Planet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .type(dto.getType())
                .radiusKm(dto.getRadiusKm())
                .massKg(dto.getMassKg())
                .orbitalPeriodDays(dto.getOrbitalPeriodDays())
                .build();
    }

    public void updateEntity(PlanetDTO dto, Planet planet) {
        if (dto == null || planet == null) {
            return;
        }
        planet.setName(dto.getName());
        planet.setType(dto.getType());
        planet.setRadiusKm(dto.getRadiusKm());
        planet.setMassKg(dto.getMassKg());
        planet.setOrbitalPeriodDays(dto.getOrbitalPeriodDays());
    }
}
