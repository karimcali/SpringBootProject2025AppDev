package ie.spring.planetsystem.SpaceManualImpl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "planets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planet_id")
    private Long planetId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;       // terrestrial, gas giant etc.

    @Column(name = "radius_km")
    private Double radiusKm;

    @Column(name = "mass_kg")
    private Double massKg;

    @Column(name = "orbital_period_days")
    private Double orbitalPeriodDays;

    @OneToMany(mappedBy = "planet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Moon> moons;
}