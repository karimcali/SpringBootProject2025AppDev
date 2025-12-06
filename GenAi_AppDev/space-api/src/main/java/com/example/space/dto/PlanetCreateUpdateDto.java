package com.example.space.dto;

import com.example.space.domain.PlanetType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanetCreateUpdateDto {

    @NotBlank
    private String name;

    @NotNull
    private PlanetType type;

    @NotNull
    @Positive
    private BigDecimal radiusKm;

    @NotNull
    @Positive
    private BigDecimal massKg;

    @NotNull
    @Positive
    private BigDecimal orbitalPeriodDays;
}
