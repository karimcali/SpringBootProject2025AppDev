package com.example.space.controller;

import com.example.space.domain.PlanetType;
import com.example.space.dto.MoonDto;
import com.example.space.dto.PlanetCreateUpdateDto;
import com.example.space.dto.PlanetDto;
import com.example.space.service.PlanetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    public List<PlanetDto> getAll(@RequestParam(name = "type", required = false) PlanetType type) {
        if (type != null) {
            return planetService.findByType(type);
        }
        return planetService.getAll();
    }

    @GetMapping("/{id}")
    public PlanetDto getById(@PathVariable Long id) {
        return planetService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanetDto create(@Valid @RequestBody PlanetCreateUpdateDto dto) {
        return planetService.create(dto);
    }

    @PutMapping("/{id}")
    public PlanetDto update(@PathVariable Long id,
                            @Valid @RequestBody PlanetCreateUpdateDto dto) {
        return planetService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        planetService.delete(id);
    }

    @GetMapping("/{planetId}/moons")
    public List<MoonDto> listMoons(@PathVariable Long planetId) {
        return planetService.listMoons(planetId);
    }

    @GetMapping("/{planetId}/moons/count")
    public Map<String, Long> countMoons(@PathVariable Long planetId) {
        long count = planetService.countMoons(planetId);
        return Map.of("planetId", planetId, "moonCount", count);
    }
}
