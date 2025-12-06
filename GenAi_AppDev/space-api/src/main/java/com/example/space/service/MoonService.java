package com.example.space.service;

import com.example.space.domain.Moon;
import com.example.space.domain.Planet;
import com.example.space.dto.MoonCreateUpdateDto;
import com.example.space.dto.MoonDto;
import com.example.space.exception.NotFoundException;
import com.example.space.mapper.MoonMapper;
import com.example.space.repository.MoonRepository;
import com.example.space.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MoonService {

    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;

    @Transactional(readOnly = true)
    public List<MoonDto> getAll() {
        return moonRepository.findAll()
                .stream()
                .map(MoonMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public MoonDto getById(Long id) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Moon " + id + " not found"));
        return MoonMapper.toDto(moon);
    }

    public MoonDto create(MoonCreateUpdateDto dto) {
        Planet planet = planetRepository.findById(dto.getPlanetId())
                .orElseThrow(() -> new NotFoundException("Planet " + dto.getPlanetId() + " not found"));
        Moon moon = MoonMapper.toEntity(dto, planet);
        Moon saved = moonRepository.save(moon);
        return MoonMapper.toDto(saved);
    }

    public MoonDto update(Long id, MoonCreateUpdateDto dto) {
        Moon existing = moonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Moon " + id + " not found"));

        Planet planet = planetRepository.findById(dto.getPlanetId())
                .orElseThrow(() -> new NotFoundException("Planet " + dto.getPlanetId() + " not found"));

        MoonMapper.updateEntity(existing, dto, planet);
        return MoonMapper.toDto(existing);
    }

    public void delete(Long id) {
        Moon existing = moonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Moon " + id + " not found"));
        moonRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<MoonDto> findByPlanet(Long planetId) {
        return moonRepository.findByPlanetId(planetId)
                .stream()
                .map(MoonMapper::toDto)
                .toList();
    }
}
