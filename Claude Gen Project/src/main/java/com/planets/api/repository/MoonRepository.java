package com.planets.api.repository;

import com.planets.api.entity.Moon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long> {

    List<Moon> findByPlanetId(Long planetId);

    @Query("SELECT COUNT(m) FROM Moon m WHERE m.planet.id = :planetId")
    Long countByPlanetId(Long planetId);
}
