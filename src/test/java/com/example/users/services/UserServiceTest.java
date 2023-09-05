package com.example.users.services;

import com.example.users.Data;
import com.example.users.database.entities.User;
import com.example.users.database.repositories.UserRepository;
import com.example.users.models.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper mapper;
    @InjectMocks
    UserServiceImpl userService;

    Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

    @Test
    @DisplayName("test find all users")
    void findAll() {
        //given
        List<User> users = data.getUsers();
        List<UserDto> userDtos = data.getUserDtos();
        //when
        when(userRepository.findAll()).thenReturn(users);
        when(mapper.map(users.get(0), UserDto.class)).thenReturn(userDtos.get(0));
        when(mapper.map(users.get(1), UserDto.class)).thenReturn(userDtos.get(1));
        when(mapper.map(users.get(2), UserDto.class)).thenReturn(userDtos.get(2));

        //then
        List<UserDto> all = userService.findAll();
        assertAll(
                () -> {
                    assertNotNull(all);
                    assertEquals(3, all.size());
                    assertEquals(1, all.get(0).getId());
                    assertEquals("Asesor de ventas", all.get(0).getPositionName());
                    assertEquals(2, all.get(1).getId());
                    assertEquals("Administrador", all.get(1).getPositionName());
                    assertEquals(3, all.get(2).getId());
                    assertEquals("Soporte", all.get(2).getPositionName());
                }
        );
    }

    @Test
    @DisplayName("test find by id OK")
    void findById() {
        //given
        long userId = 1;
        User user = data.getUser();
        UserDto userDto = data.getUserDto();
        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mapper.map(user, UserDto.class)).thenReturn(userDto);
        //then
        UserDto byId = userService.findById(userId);
        assertAll(
                () -> {
                    assertNotNull(byId);
                    assertEquals(1, byId.getId());
                    assertEquals("Juan Garcia", byId.getName());
                }
        );

    }

    @Test
    @DisplayName("test find by id null")
    void findByIdNull() {
        //given
        long userId = 100;
        //when
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        //then
        UserDto byId = userService.findById(userId);
        assertAll(
                () -> {
                    assertNull(byId);
                }
        );

    }
}