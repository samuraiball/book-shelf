package com.bookshelf.controller;

import com.bookshelf.dto.UserDto;
import com.bookshelf.dto.UserResponseDto;
import com.bookshelf.entity.UserEntity;
import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    private MockMvc mockMvc;

    private UserDto userDto;

    private UserEntity userEntity;

    private String userJsonRequest;

    private String userJsonResponse;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userDto = new UserDto("mohe@example.com", "password", "mohe heno");

        userEntity = new UserEntity("550e8400-e29b-41d4-a716-446655440004","mohe@example.com",
                "$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq", "mohe heno",null);

        UserResponseDto userResponseDto = new UserResponseDto("550e8400-e29b-41d4-a716-446655440004",
                "mohe heno", "mohe@example.com");

        userJsonResponse = objectMapper.writeValueAsString(userResponseDto);
        userJsonRequest = objectMapper.writeValueAsString(userDto);

    }

    @Test
    public void 正常_ユーザ作成完了() throws Exception {

        when(userService.createUser(any())).thenReturn(userEntity);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonRequest))
                .andExpect(content().json(userJsonResponse))
                .andExpect(status().isCreated());
    }

    @Test
    public void 異常_ユーザがすでに存在する_409() throws Exception {

        when(userService.createUser(any())).thenThrow(UserAlreadyExistException.class);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonRequest))
                .andExpect(status().isConflict());
    }
}