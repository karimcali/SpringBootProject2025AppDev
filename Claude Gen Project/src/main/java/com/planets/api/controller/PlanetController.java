package com.planets.api.controller;

import com.planets.api.dto.MoonCountDTO;
import com.planets.api.dto.PlanetDTO;
import com.planets.api.service.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
@Tag(name = "Planet", description = "Planet management APIs")
@SecurityRequirement(name = "basicAuth")
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get all planets", description = "Retrieve all planets in the system")
    public ResponseEntity<List<PlanetDTO>> getAllPlanets() {
        return ResponseEntity.ok(planetService.getAllPlanets());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get planet by ID", description = "Retrieve a specific planet by its ID")
    public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable Long id) {
        return ResponseEntity.ok(planetService.getPlanetById(id));
    }

    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get planets by type", description = "Filter planets by their type")
    public ResponseEntity<List<PlanetDTO>> getPlanetsByType(@PathVariable String type) {
        return ResponseEntity.ok(planetService.getPlanetsByType(type));
    }

    @GetMapping("/{id}/moon-count")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Count moons per planet", description = "Get the count of moons for a specific planet")
    public ResponseEntity<MoonCountDTO> getMoonCount(@PathVariable Long id) {
        return ResponseEntity.ok(planetService.getMoonCountByPlanetId(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create planet", description = "Create a new planet")
    public ResponseEntity<PlanetDTO> createPlanet(@Valid @RequestBody PlanetDTO planetDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.createPlanet(planetDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update planet", description = "Update an existing planet")
    public ResponseEntity<PlanetDTO> updatePlanet(
            @PathVariable Long id,
            @Valid @RequestBody PlanetDTO planetDTO) {
        return ResponseEntity.ok(planetService.updatePlanet(id, planetDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete planet", description = "Delete a planet by ID")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}
