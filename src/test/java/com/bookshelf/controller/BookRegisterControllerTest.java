package com.bookshelf.controller;

import com.bookshelf.dto.BookDto;
import com.bookshelf.entity.BookEntity;
import com.bookshelf.service.BookRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterControllerTest {

    @Autowired
    BookRegisterController bookRegisterController;

    @MockBean
    BookRegisterService bookRegisterService;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private String requestJson;

    private BookEntity bookEntity;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookRegisterController).build();

        bookEntity = new BookEntity();
        bookEntity.setId(0);

        BookDto bookDto = new BookDto("Effective Java", "IT");
        requestJson = objectMapper.writeValueAsString(bookDto);

    }

    @Test
    public void 正常系_本の登録_201のレスポンス() throws Exception {

        when(bookRegisterService.registerBook(any())).thenReturn(bookEntity);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/book/" + bookEntity.getId()));
    }

    @Test
    public void 正常系_本の取得_200のレスポンス() throws Exception{

        mockMvc.perform(get("/book/0"))
                .andExpect(status().isOk());
    }

}