package com.example.space.controller;

import com.example.space.dto.MoonCreateUpdateDto;
import com.example.space.dto.MoonDto;
import com.example.space.service.MoonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moons")
@RequiredArgsConstructor
public class MoonController {

    private final MoonService moonService;

    @GetMapping
    public List<MoonDto> getAll() {
        return moonService.getAll();
    }

    @GetMapping("/{id}")
    public MoonDto getById(@PathVariable Long id) {
        return moonService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MoonDto create(@Valid @RequestBody MoonCreateUpdateDto dto) {
        return moonService.create(dto);
    }

    @PutMapping("/{id}")
    public MoonDto update(@PathVariable Long id,
                          @Valid @RequestBody MoonCreateUpdateDto dto) {
        return moonService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        moonService.delete(id);
    }
}
