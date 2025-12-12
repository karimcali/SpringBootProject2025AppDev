package ie.spring.planetsystem.SpaceManualImpl.mappers;

import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetSummaryDTO;
import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import ie.spring.planetsystem.SpaceManualImpl.dto.MoonDTO;
import ie.spring.planetsystem.SpaceManualImpl.entities.Moon;
import ie.spring.planetsystem.SpaceManualImpl.entities.MyUser;
import ie.spring.planetsystem.SpaceManualImpl.dto.MyUserDTO;

public class Mappers {

    public static PlanetDTO mapPlanetToDTO(Planet planet) {
        if (planet == null) {
            return null;
        }

        Integer moonCount = null;
        if (planet.getMoons() != null) {
            moonCount = planet.getMoons().size();
        }

        return PlanetDTO.builder()
                .planetId(planet.getPlanetId())
                .name(planet.getName())
                .type(planet.getType())
                .radiusKm(planet.getRadiusKm())
                .massKg(planet.getMassKg())
                .orbitalPeriodDays(planet.getOrbitalPeriodDays())
                .moonCount(moonCount)
                .build();
    }

    public static PlanetSummaryDTO mapPlanetToSummaryDTO(Planet planet) {
        if (planet == null) {
            return null;
        }
        return new PlanetSummaryDTO(
                planet.getPlanetId(),
                planet.getName(),
                planet.getMassKg()
        );
    }


    public static MoonDTO mapMoonToDTO(Moon moon) {
    if (moon == null) {
        return null;
    }

    Long planetId = null;
    String planetName = null;
    if (moon.getPlanet() != null) {
        planetId = moon.getPlanet().getPlanetId();
        planetName = moon.getPlanet().getName();
    }

    return new MoonDTO(
            moon.getMoonId(),
            moon.getName(),
            moon.getDiameterKm(),
            moon.getOrbitalPeriodDays(),
            planetId,
            planetName
    );
    
    }


     public static MyUserDTO mapMyUserToMyUserDTO(MyUser user) {
        if (user == null) {
            return null;
        }

        return new MyUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole()
                
        );
    }
}
