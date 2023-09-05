package com.example.users.services.interfaces;

import com.example.users.database.entities.Position;
import com.example.users.exceptions.position.PositionNotFoundException;
import com.example.users.models.position.PositionDto;
import com.example.users.models.position.PositionToSaveDto;

import java.util.List;

public interface PositionService {
    PositionDto save(PositionToSaveDto positionToSave);
    List<PositionDto> getAll();
    Position findById(int id);
    PositionDto update(PositionDto positionDto) throws PositionNotFoundException;
}
