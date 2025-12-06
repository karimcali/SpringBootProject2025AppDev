package com.example.space.service;

import com.example.space.domain.Planet;
import com.example.space.domain.PlanetType;
import com.example.space.dto.MoonDto;
import com.example.space.dto.PlanetCreateUpdateDto;
import com.example.space.dto.PlanetDto;
import com.example.space.exception.NotFoundException;
import com.example.space.mapper.MoonMapper;
import com.example.space.mapper.PlanetMapper;
import com.example.space.repository.MoonRepository;
import com.example.space.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;

    @Transactional(readOnly = true)
    public List<PlanetDto> getAll() {
        return planetRepository.findAll()
                .stream()
                .map(PlanetMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlanetDto getById(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet " + id + " not found"));
        return PlanetMapper.toDto(planet);
    }

    public PlanetDto create(PlanetCreateUpdateDto dto) {
        Planet entity = PlanetMapper.toEntity(dto);
        Planet saved = planetRepository.save(entity);
        return PlanetMapper.toDto(saved);
    }

    public PlanetDto update(Long id, PlanetCreateUpdateDto dto) {
        Planet existing = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet " + id + " not found"));
        PlanetMapper.updateEntity(existing, dto);
        return PlanetMapper.toDto(existing);
    }

    public void delete(Long id) {
        Planet existing = planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planet " + id + " not found"));
        planetRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<PlanetDto> findByType(PlanetType type) {
        return planetRepository.findByType(type)
                .stream()
                .map(PlanetMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MoonDto> listMoons(Long planetId) {
        if (!planetRepository.existsById(planetId)) {
            throw new NotFoundException("Planet " + planetId + " not found");
        }
        return moonRepository.findByPlanetId(planetId)
                .stream()
                .map(MoonMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public long countMoons(Long planetId) {
        if (!planetRepository.existsById(planetId)) {
            throw new NotFoundException("Planet " + planetId + " not found");
        }
        return moonRepository.countByPlanetId(planetId);
    }
}
