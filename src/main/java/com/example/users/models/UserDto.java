package com.example.users.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
    private long id;
    private String name;
    private int age;
    private String positionName;
    private LocalDate dateEntry;
}
