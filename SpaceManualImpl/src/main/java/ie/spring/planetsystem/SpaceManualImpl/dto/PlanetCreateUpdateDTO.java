package ie.spring.planetsystem.SpaceManualImpl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PlanetCreateUpdateDTO(
        @NotBlank(message = "Planet name is required")
        String name,

        @NotBlank(message = "Planet type is required")
        String type,

        @Positive(message = "Radius must be positive")
        Double radiusKm,

        @Positive(message = "Mass must be positive")
        Double massKg,

        @Positive(message = "Orbital period must be positive")
        Double orbitalPeriodDays
) {}
