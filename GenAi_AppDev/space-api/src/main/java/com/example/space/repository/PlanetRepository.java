package com.example.space.repository;

import com.example.space.domain.Planet;
import com.example.space.domain.PlanetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    List<Planet> findByType(PlanetType type);
}
