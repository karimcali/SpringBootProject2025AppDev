package com.example.space.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanetType type;

    @Column(name = "radius_km", nullable = false, precision = 20, scale = 4)
    private BigDecimal radiusKm;

    @Column(name = "mass_kg", nullable = false, precision = 38, scale = 2)
    private BigDecimal massKg;

    @Column(name = "orbital_period_days", nullable = false, precision = 20, scale = 4)
    private BigDecimal orbitalPeriodDays;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Moon> moons = new ArrayList<>();
}
