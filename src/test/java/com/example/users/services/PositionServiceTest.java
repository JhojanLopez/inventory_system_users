package com.example.users.services;

import com.example.users.Data;
import com.example.users.database.entities.Position;
import com.example.users.database.repositories.PositionRepository;
import com.example.users.exceptions.position.PositionNotFoundException;
import com.example.users.models.position.PositionDto;
import com.example.users.models.position.PositionToSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PositionServiceTest {
    @Mock
    PositionRepository repository;
    @Mock
    ModelMapper mapper;
    @InjectMocks
    PositionServiceImpl positionService;

    Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

    @Test
    @DisplayName("test save position")
    void save() {
        //given
        PositionToSaveDto positionToSaveDto = data.getPositionToSaveDto();
        Position positionEntityToSave = data.getPositionEntityToSave();
        Position positionEntitySaved = data.getPositionEntitySaved();
        PositionDto positionDtoSaved = data.getPositionDtoSaved();

        //when
        when(mapper.map(positionToSaveDto, Position.class)).thenReturn(data.getPositionEntityToSave());
        when(repository.save(positionEntityToSave)).thenReturn(positionEntitySaved);
        when(mapper.map(positionEntityToSave, PositionDto.class)).thenReturn(positionDtoSaved);

        //then
        PositionDto saved = positionService.save(positionToSaveDto);
        assertAll(() -> {
            assertNotNull(saved);
            assertEquals(3, saved.getId());
        });
    }

    @Test
    @DisplayName("test get all positions")
    void getAll() {
        //given
        List<Position> positions = data.getPositionsSaved();

        //when
        when(repository.findAll()).thenReturn(positions);

        //then
        List<PositionDto> all = positionService.getAll();
        assertAll(() -> {
            assertNotNull(all);
            assertEquals(3, all.size());
        });

    }

    @Test
    @DisplayName("test update position")
    void update() throws PositionNotFoundException {
        //given
        PositionDto positionDtoToUpdate = data.getPositionDtoToUpdate();
        Position positionToUpdate = data.getPositionToUpdate();
        Position positionUpdated = data.getPositionUpdated();
        PositionDto positionDtoUpdated = data.getPositionDtoUpdated();

        //when
        when(repository.findById(positionDtoToUpdate.getId())).thenReturn(Optional.of(positionToUpdate));
        when(repository.save(positionToUpdate)).thenReturn(positionUpdated);
        when(mapper.map(positionUpdated, PositionDto.class)).thenReturn(positionDtoUpdated);


        // then
        PositionDto updated = positionService.update(positionDtoToUpdate);
        verify(repository).save(positionToUpdate);
        assertEquals(positionUpdated.getName(), updated.getName());
    }

    @Test
    @DisplayName("position not found exception")
    void updatePositionNotFound() throws PositionNotFoundException {
        //given
        PositionDto positionDtoToUpdate = data.getPositionDtoToUpdate();
        positionDtoToUpdate.setId(100);

        //when
        when(repository.findById(positionDtoToUpdate.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(PositionNotFoundException.class, () -> positionService.update(positionDtoToUpdate));
        verify(repository).findById(positionDtoToUpdate.getId());
    }
}