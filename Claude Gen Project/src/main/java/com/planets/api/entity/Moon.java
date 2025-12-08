package com.planets.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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

    @NotBlank(message = "Moon name is required")
    @Column(nullable = false)
    private String name;

    @Positive(message = "Diameter must be positive")
    @Column(nullable = false)
    private Double diameterKm;

    @Positive(message = "Orbital period must be positive")
    @Column(nullable = false)
    private Double orbitalPeriodDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    @NotNull(message = "Planet is required")
    private Planet planet;
}
