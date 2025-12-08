package com.planets.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoonDTO {

    private Long id;

    @NotBlank(message = "Moon name is required")
    private String name;

    @Positive(message = "Diameter must be positive")
    private Double diameterKm;

    @Positive(message = "Orbital period must be positive")
    private Double orbitalPeriodDays;

    @NotNull(message = "Planet ID is required")
    private Long planetId;

    private String planetName;
}
