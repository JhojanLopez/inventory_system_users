package com.example.users.models.position;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionToSaveDto {
    private String name;
}
