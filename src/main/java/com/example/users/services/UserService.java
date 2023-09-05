package com.example.users.services;

import com.example.users.models.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long id);
}
