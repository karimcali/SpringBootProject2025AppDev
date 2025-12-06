package com.example.space.repository;

import com.example.space.domain.Moon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoonRepository extends JpaRepository<Moon, Long> {
    List<Moon> findByPlanetId(Long planetId);
    long countByPlanetId(Long planetId);
}
