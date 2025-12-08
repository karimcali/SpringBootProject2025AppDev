package com.planets.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

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

    @NotBlank(message = "Planet name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Planet type is required")
    @Column(nullable = false)
    private String type; // e.g., Terrestrial, Gas Giant, Ice Giant, Dwarf

    @Positive(message = "Radius must be positive")
    @Column(nullable = false)
    private Double radiusKm;

    @Positive(message = "Mass must be positive")
    @Column(nullable = false)
    private Double massKg;

    @Positive(message = "Orbital period must be positive")
    @Column(nullable = false)
    private Double orbitalPeriodDays;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Moon> moons = new ArrayList<>();
}
