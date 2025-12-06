package com.example.space.mapper;

import com.example.space.domain.Planet;
import com.example.space.dto.PlanetCreateUpdateDto;
import com.example.space.dto.PlanetDto;

public class PlanetMapper {

    public static PlanetDto toDto(Planet entity) {
        PlanetDto dto = new PlanetDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setRadiusKm(entity.getRadiusKm());
        dto.setMassKg(entity.getMassKg());
        dto.setOrbitalPeriodDays(entity.getOrbitalPeriodDays());
        return dto;
    }

    public static Planet toEntity(PlanetCreateUpdateDto dto) {
        return Planet.builder()
                .name(dto.getName())
                .type(dto.getType())
                .radiusKm(dto.getRadiusKm())
                .massKg(dto.getMassKg())
                .orbitalPeriodDays(dto.getOrbitalPeriodDays())
                .build();
    }

    public static void updateEntity(Planet entity, PlanetCreateUpdateDto dto) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setRadiusKm(dto.getRadiusKm());
        entity.setMassKg(dto.getMassKg());
        entity.setOrbitalPeriodDays(dto.getOrbitalPeriodDays());
    }
}
