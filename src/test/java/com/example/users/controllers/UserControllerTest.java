package com.example.users.controllers;

import com.example.users.Data;
import com.example.users.models.UserDto;
import com.example.users.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
    }
    @Test
    @DisplayName("test get all OK")
    void getAll() throws Exception {
        //given
        List<UserDto> positionDtos = data.getUserDtos();
        //when
        when(userService.findAll()).thenReturn(positionDtos);
        //then
        mockMvc.perform(get("/api/v1/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Juan Garcia"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Jhohan Lopez"))
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[2].name").value("Kevin Casierra"))
        ;

    }
    @Test
    @DisplayName("test get all No found")
    void getAllEmpty() throws Exception {

        //when
        when(userService.findAll()).thenReturn(Collections.emptyList());
        //then
        mockMvc.perform(get("/api/v1/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;

    }

    @Test
    @DisplayName("test by id OK")
    void getByid() throws Exception {
        //given
        long userId = 1;
        UserDto userDto = data.getUserDto();
        //when
        when(userService.findById(userId)).thenReturn(userDto);
        //then
        mockMvc.perform(get("/api/v1/user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Juan Garcia"))
        ;

    }
    @Test
    @DisplayName("test get by id No found")
    void getByIdNoFound() throws Exception {
        //given
        long userId = 100;
        //when
        when(userService.findById(userId)).thenReturn(null);
        //then
        mockMvc.perform(get("/api/v1/user/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;

    }
}