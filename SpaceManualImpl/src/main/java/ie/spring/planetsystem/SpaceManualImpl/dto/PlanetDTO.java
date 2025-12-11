package ie.spring.planetsystem.SpaceManualImpl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetDTO {

    private Long planetId;

    @NotBlank(message = "Planet name is required")
    private String name;

    @NotBlank(message = "Planet type is required")
    private String type;

    @Positive(message = "Radius must be positive")
    private Double radiusKm;

    @Positive(message = "Mass must be positive")
    private Double massKg;

    @Positive(message = "Orbital period must be positive")
    private Double orbitalPeriodDays;

    // count injfo we can set in mapper
    private Integer moonCount;
}

