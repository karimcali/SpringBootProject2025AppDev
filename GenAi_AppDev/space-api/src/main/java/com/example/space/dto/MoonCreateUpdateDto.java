package com.example.space.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoonCreateUpdateDto {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal diameterKm;

    @NotNull
    @Positive
    private BigDecimal orbitalPeriodDays;

    @NotNull
    @Positive
    private Long planetId;
}
