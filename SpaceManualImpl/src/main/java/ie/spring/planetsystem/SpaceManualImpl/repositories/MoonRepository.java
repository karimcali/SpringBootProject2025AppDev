package ie.spring.planetsystem.SpaceManualImpl.repositories;

import ie.spring.planetsystem.SpaceManualImpl.entities.Moon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoonRepository extends JpaRepository<Moon, Long> {

    List<Moon> findAllByPlanet_NameIgnoreCase(String planetName);

    long countByPlanet_NameIgnoreCase(String planetName);

}

