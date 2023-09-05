package com.example.users;

import com.example.users.database.entities.Position;
import com.example.users.database.entities.User;
import com.example.users.models.position.PositionDto;
import com.example.users.models.position.PositionToSaveDto;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

public class Data {
    private List<Position> positionsSaved;
    private PositionToSaveDto positionToSaveDto;
    private Position positionEntityToSave;
    private Position positionEntitySaved;
    private PositionDto positionDtoSaved;
    private PositionDto positionDtoToUpdate;
    private Position positionToUpdate;
    private Position positionUpdated;
    private PositionDto positionDtoUpdated;
    public List<Position> getPositionsSaved() {
        return List.of(
                Position.builder()
                        .id(1)
                        .name("Asesor de ventas")
                        .createdAt(LocalDateTime.now())
                        .build(),
                Position.builder()
                        .id(2)
                        .name("Administrador y soporte")
                        .createdAt(LocalDateTime.now())
                        .build(),
                Position.builder()
                        .id(3)
                        .name("Empleado")
                        .createdAt(LocalDateTime.now())
                        .build());
    }

    public PositionToSaveDto getPositionToSaveDto() {
        return PositionToSaveDto.builder()
                .name("Empleado")
                .build();
    }

    public Position getPositionEntityToSave() {
        return Position.builder()
                .name("Empleado")
                .build();
    }

    public Position getPositionEntitySaved() {
        return getPositionsSaved().get(2);
    }

    public PositionDto getPositionDtoSaved() {
        return PositionDto.builder()
                .id(3)
                .name("Empleado")
                .build();
    }

    public PositionDto getPositionDtoToUpdate() {
        return PositionDto.builder()
                .id(3)
                .name("Empleado general")
                .build();
    }

    public Position getPositionToUpdate() {
        return getPositionsSaved().get(2);
    }

    public Position getPositionUpdated() {
        Position position = getPositionsSaved().get(2);
        position.setName(getPositionDtoToUpdate().getName());
        position.setUpdatedAt(LocalDateTime.now());
        return position;
    }

    public PositionDto getPositionDtoUpdated() {
        return PositionDto.builder()
                .id(3)
                .name("Empleado general")
                .build();
    }
}
