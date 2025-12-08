package com.planets.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetDTO {

    private Long id;

    @NotBlank(message = "Planet name is required")
    private String name;

    @NotBlank(message = "Planet type is required")
    private String type;

    @Positive(message = "Radius must be positive")
    private Double radiusKm;

    @Positive(message = "Mass must be positive")
    private Double massKg;

    @Positive(message = "Orbital period must be positive")
    private Double orbitalPeriodDays;

    private Integer moonCount;
}
