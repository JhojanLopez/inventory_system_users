package com.example.users;

import com.example.users.database.entities.Position;
import com.example.users.database.entities.User;
import com.example.users.models.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Data {
    private List<User> users;
    private List<Position> positions;
    private List<UserDto> userDtos;
    private User user;
    private UserDto userDto;

    public List<User> getUsers() {
        return List.of(
                User.builder()
                        .id(1)
                        .name("Juan Garcia")
                        .age(30)
                        .position(getPositions().get(0))
                        .dateEntry(LocalDate.now().minusDays(2))
                        .createdAt(LocalDateTime.now())
                        .build(),
                User.builder()
                        .id(2)
                        .name("Jhohan Lopez")
                        .age(23)
                        .position(getPositions().get(1))
                        .dateEntry(LocalDate.now().minusDays(20))
                        .createdAt(LocalDateTime.now())
                        .build(),
                User.builder()
                        .id(3)
                        .name("Kevin Casierra")
                        .age(22)
                        .position(getPositions().get(2))
                        .dateEntry(LocalDate.now().minusDays(10))
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

    public List<Position> getPositions() {
        return List.of(
                Position.builder()
                        .id(1)
                        .name("Asesor de ventas")
                        .createdAt(LocalDateTime.now())
                        .build(),
                Position.builder()
                        .id(2)
                        .name("Administrador")
                        .createdAt(LocalDateTime.now())
                        .build(),
                Position.builder()
                        .id(3)
                        .name("Soporte")
                        .createdAt(LocalDateTime.now())
                        .build());
    }

    public List<UserDto> getUserDtos() {
        return List.of(
                UserDto.builder()
                        .id(getUsers().get(0).getId())
                        .name(getUsers().get(0).getName())
                        .age(getUsers().get(0).getAge())
                        .positionName(getPositions().get(0).getName())
                        .dateEntry(getUsers().get(0).getDateEntry())
                        .build(),
                UserDto.builder()
                        .id(getUsers().get(1).getId())
                        .name(getUsers().get(1).getName())
                        .age(getUsers().get(1).getAge())
                        .positionName(getPositions().get(1).getName())
                        .dateEntry(getUsers().get(1).getDateEntry())
                        .build(),
                UserDto.builder()
                        .id(getUsers().get(2).getId())
                        .name(getUsers().get(2).getName())
                        .age(getUsers().get(2).getAge())
                        .positionName(getPositions().get(2).getName())
                        .dateEntry(getUsers().get(2).getDateEntry())
                        .build()
        );
    }

    public User getUser() {
        return getUsers().get(0);
    }

    public UserDto getUserDto() {
        return UserDto.builder()
                .id(getUser().getId())
                .name(getUser().getName())
                .age(getUser().getAge())
                .positionName(getUser().getName())
                .dateEntry(getUser().getDateEntry())
                .build();
    }
}
