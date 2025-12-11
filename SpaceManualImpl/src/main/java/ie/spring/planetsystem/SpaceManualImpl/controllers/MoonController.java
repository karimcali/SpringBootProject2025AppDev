package ie.spring.planetsystem.SpaceManualImpl.controllers;

import ie.spring.planetsystem.SpaceManualImpl.dto.MoonCreateDTO;
import ie.spring.planetsystem.SpaceManualImpl.dto.MoonDTO;
import ie.spring.planetsystem.SpaceManualImpl.service.MoonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/moons")
@RequiredArgsConstructor
public class MoonController {

    private final MoonService moonService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public List<MoonDTO> getAllMoons() {
        return moonService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public MoonDTO getMoonById(@PathVariable Long id) {
        return moonService.findById(id);
    }

    @GetMapping("/planet/{planetName}")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public List<MoonDTO> getMoonsByPlanetName(@PathVariable String planetName) {
        return moonService.findByPlanetName(planetName);
    }

    @GetMapping("/planet/{planetName}/count")
    @PreAuthorize("hasAnyAuthority('STUDENT','STAFF','ADMIN')")
    public long countMoonsByPlanetName(@PathVariable String planetName) {
        return moonService.countByPlanetName(planetName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public MoonDTO createMoon(@Valid @RequestBody MoonCreateDTO dto) {
        return moonService.createMoon(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public MoonDTO updateMoon(@PathVariable Long id,
                              @Valid @RequestBody MoonCreateDTO dto) {
        return moonService.updateMoon(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public void deleteMoon(@PathVariable Long id) {
        moonService.deleteById(id);
    }
}
