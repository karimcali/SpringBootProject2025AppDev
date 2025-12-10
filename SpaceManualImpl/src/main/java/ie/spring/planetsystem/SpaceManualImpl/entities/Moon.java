package ie.spring.planetsystem.SpaceManualImpl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moon {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moon_id")
    private Long moonId;

    @Column(nullable = false)
    private String name;

    @Column(name = "diameter_km")
    private Double diameterKm;

    @Column(name = "orbital_period_days")
    private Double orbitalPeriodDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;
}
