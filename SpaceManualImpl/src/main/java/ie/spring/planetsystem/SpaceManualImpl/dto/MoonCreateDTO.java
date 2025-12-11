package ie.spring.planetsystem.SpaceManualImpl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MoonCreateDTO(

        @NotBlank(message = "Moon name is required")
        String name,

        @Positive(message = "Diameter must be positive")
        Double diameterKm,

        @Positive(message = "Orbital period must be positive")
        Double orbitalPeriodDays,

        @NotNull(message = "Planet id is required")
        Long planetId
) {}
