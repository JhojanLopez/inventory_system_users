package com.example.users.services;

import com.example.users.database.entities.Position;
import com.example.users.database.repositories.PositionRepository;
import com.example.users.exceptions.position.PositionNotFoundException;
import com.example.users.models.position.PositionDto;
import com.example.users.models.position.PositionToSaveDto;
import com.example.users.services.interfaces.PositionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository repository;
    private final ModelMapper mapper;

    @Override
    public PositionDto save(PositionToSaveDto positionToSave) {
        Position entityToSave = mapper.map(positionToSave, Position.class);
        repository.save(entityToSave);
        return mapper.map(entityToSave, PositionDto.class);
    }

    @Override
    public List<PositionDto> getAll() {
        List<Position> all = repository.findAll();
        return all.stream().map(e -> mapper.map(e, PositionDto.class)).toList();
    }

    @Override
    public Position findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PositionDto update(PositionDto positionDto) throws PositionNotFoundException {
        Position positionToUpdate = this.findById(positionDto.getId());

        if (positionToUpdate == null) throw new PositionNotFoundException("The position to update does not exist.");

        Position updated = repository.save(positionToUpdate);
        return mapper.map(updated, PositionDto.class);
    }
}
