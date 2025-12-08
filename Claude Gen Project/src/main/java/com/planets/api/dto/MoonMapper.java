package com.planets.api.dto;

import com.planets.api.entity.Moon;
import org.springframework.stereotype.Component;

@Component
public class MoonMapper {

    public MoonDTO toDTO(Moon moon) {
        if (moon == null) {
            return null;
        }
        return MoonDTO.builder()
                .id(moon.getId())
                .name(moon.getName())
                .diameterKm(moon.getDiameterKm())
                .orbitalPeriodDays(moon.getOrbitalPeriodDays())
                .planetId(moon.getPlanet() != null ? moon.getPlanet().getId() : null)
                .planetName(moon.getPlanet() != null ? moon.getPlanet().getName() : null)
                .build();
    }

    public Moon toEntity(MoonDTO dto) {
        if (dto == null) {
            return null;
        }
        return Moon.builder()
                .id(dto.getId())
                .name(dto.getName())
                .diameterKm(dto.getDiameterKm())
                .orbitalPeriodDays(dto.getOrbitalPeriodDays())
                .build();
    }

    public void updateEntity(MoonDTO dto, Moon moon) {
        if (dto == null || moon == null) {
            return;
        }
        moon.setName(dto.getName());
        moon.setDiameterKm(dto.getDiameterKm());
        moon.setOrbitalPeriodDays(dto.getOrbitalPeriodDays());
    }
}
