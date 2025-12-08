package com.planets.api.controller;

import com.planets.api.dto.MoonDTO;
import com.planets.api.service.MoonService;
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
@RequestMapping("/api/moons")
@RequiredArgsConstructor
@Tag(name = "Moon", description = "Moon management APIs")
@SecurityRequirement(name = "basicAuth")
public class MoonController {

    private final MoonService moonService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get all moons", description = "Retrieve all moons in the system")
    public ResponseEntity<List<MoonDTO>> getAllMoons() {
        return ResponseEntity.ok(moonService.getAllMoons());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get moon by ID", description = "Retrieve a specific moon by its ID")
    public ResponseEntity<MoonDTO> getMoonById(@PathVariable Long id) {
        return ResponseEntity.ok(moonService.getMoonById(id));
    }

    @GetMapping("/planet/{planetId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'STUDENT')")
    @Operation(summary = "Get moons by planet", description = "List all moons belonging to a specific planet")
    public ResponseEntity<List<MoonDTO>> getMoonsByPlanet(@PathVariable Long planetId) {
        return ResponseEntity.ok(moonService.getMoonsByPlanetId(planetId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create moon", description = "Create a new moon")
    public ResponseEntity<MoonDTO> createMoon(@Valid @RequestBody MoonDTO moonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moonService.createMoon(moonDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update moon", description = "Update an existing moon")
    public ResponseEntity<MoonDTO> updateMoon(
            @PathVariable Long id,
            @Valid @RequestBody MoonDTO moonDTO) {
        return ResponseEntity.ok(moonService.updateMoon(id, moonDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete moon", description = "Delete a moon by ID")
    public ResponseEntity<Void> deleteMoon(@PathVariable Long id) {
        moonService.deleteMoon(id);
        return ResponseEntity.noContent().build();
    }
}
