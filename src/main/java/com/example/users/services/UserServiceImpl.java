package com.example.users.services;

import com.example.users.database.entities.User;
import com.example.users.database.repositories.UserRepository;
import com.example.users.models.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<UserDto> findAll() {
        List<User> all = repository.findAll();
        return all.stream().map(e-> mapper.map(e, UserDto.class)).toList();
    }

    @Override
    public UserDto findById(long id) {
        return repository.findById(id).map(e-> mapper.map(e, UserDto.class)).orElse(null);
    }
}
