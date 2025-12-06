package com.example.space.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "moons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "diameter_km", nullable = false, precision = 20, scale = 4)
    private BigDecimal diameterKm;

    @Column(name = "orbital_period_days", nullable = false, precision = 20, scale = 4)
    private BigDecimal orbitalPeriodDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;
}
