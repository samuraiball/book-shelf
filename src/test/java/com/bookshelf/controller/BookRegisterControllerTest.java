package com.bookshelf.controller;

import com.bookshelf.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterControllerTest {

    @Autowired
    BookRegisterController bookRegisterController;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private String requestJson;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new BookRegisterController()).build();
        BookDto bookDto = new BookDto("Effective Java", "IT");
        requestJson = objectMapper.writeValueAsString(bookDto);
    }

    @Test
    public void 正常系_201のレスポンス() throws Exception {
        mockMvc.perform(post("/book/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

}