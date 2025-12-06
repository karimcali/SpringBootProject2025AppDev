package com.example.space.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoonDto {
    private Long id;
    private String name;
    private BigDecimal diameterKm;
    private BigDecimal orbitalPeriodDays;
    private Long planetId;
}
