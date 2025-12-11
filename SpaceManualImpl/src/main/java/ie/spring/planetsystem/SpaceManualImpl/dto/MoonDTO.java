package ie.spring.planetsystem.SpaceManualImpl.dto;

public record MoonDTO(
        Long moonId,
        String name,
        Double diameterKm,
        Double orbitalPeriodDays,
        Long planetId,
        String planetName
) {}
