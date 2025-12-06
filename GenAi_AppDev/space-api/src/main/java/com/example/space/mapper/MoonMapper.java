package com.example.space.mapper;

import com.example.space.domain.Moon;
import com.example.space.domain.Planet;
import com.example.space.dto.MoonCreateUpdateDto;
import com.example.space.dto.MoonDto;

public class MoonMapper {

    public static MoonDto toDto(Moon entity) {
        MoonDto dto = new MoonDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDiameterKm(entity.getDiameterKm());
        dto.setOrbitalPeriodDays(entity.getOrbitalPeriodDays());
        dto.setPlanetId(entity.getPlanet().getId());
        return dto;
    }

    public static Moon toEntity(MoonCreateUpdateDto dto, Planet planet) {
        return Moon.builder()
                .name(dto.getName())
                .diameterKm(dto.getDiameterKm())
                .orbitalPeriodDays(dto.getOrbitalPeriodDays())
                .planet(planet)
                .build();
    }

    public static void updateEntity(Moon entity, MoonCreateUpdateDto dto, Planet planet) {
        entity.setName(dto.getName());
        entity.setDiameterKm(dto.getDiameterKm());
        entity.setOrbitalPeriodDays(dto.getOrbitalPeriodDays());
        entity.setPlanet(planet);
    }
}
