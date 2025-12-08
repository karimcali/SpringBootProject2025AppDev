package com.planets.api.repository;

import com.planets.api.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findByType(String type);

    @Query("SELECT p FROM Planet p LEFT JOIN FETCH p.moons WHERE p.id = :id")
    Planet findByIdWithMoons(Long id);
}
