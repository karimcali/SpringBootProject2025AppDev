package com.example.space.dto;

import com.example.space.domain.PlanetType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanetDto {
    private Long id;
    private String name;
    private PlanetType type;
    private BigDecimal radiusKm;
    private BigDecimal massKg;
    private BigDecimal orbitalPeriodDays;
}
