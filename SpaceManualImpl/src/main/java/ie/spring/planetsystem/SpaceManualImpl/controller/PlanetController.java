package ie.spring.planetsystem.SpaceManualImpl.controllers;

import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetCreateUpdateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.PlanetSummaryDTO;
import ie.spring.planetsystem.SpaceManualImpl.service.PlanetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public List<PlanetDTO> getAllPlanets() {
        return planetService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public PlanetDTO getPlanetById(@PathVariable Long id) {
        return planetService.findById(id);
    }

    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public List<PlanetDTO> getPlanetsByType(@PathVariable String type) {
        return planetService.findByType(type);
    }

    @GetMapping("/{id}/summary")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public PlanetSummaryDTO getPlanetSummary(@PathVariable Long id) {
        return planetService.getPlanetSummary(id);
    }

    @GetMapping("/{id}/moons/count")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public long getMoonCountByPlanetId(@PathVariable Long id) {
        return planetService.getMoonCountByPlanetId(id);
    }

    @GetMapping("/name/{name}/moons/count")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public long getMoonCountByPlanetName(@PathVariable String name) {
        return planetService.getMoonCountByPlanetName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public PlanetDTO createPlanet(@Valid @RequestBody PlanetCreateUpdateDTO dto) {
        return planetService.createPlanet(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public PlanetDTO updatePlanet(@PathVariable Long id,
                                  @Valid @RequestBody PlanetCreateUpdateDTO dto) {
        return planetService.updatePlanet(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }
}