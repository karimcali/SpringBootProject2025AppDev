package ie.spring.planetsystem.SpaceManualImpl.repositories;

import ie.spring.planetsystem.SpaceManualImpl.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> findByNameIgnoreCase(String name);

    List<Planet> findAllByTypeIgnoreCase(String type);

}

