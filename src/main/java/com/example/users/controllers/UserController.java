package com.example.users.controllers;

import com.example.users.models.UserDto;
import com.example.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDto> all = userService.findAll();
        if (all.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId) {
        UserDto byId = userService.findById(userId);
        if (byId == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(byId);
    }
}
