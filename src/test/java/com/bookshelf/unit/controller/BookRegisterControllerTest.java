package com.bookshelf.unit.controller;

import com.bookshelf.unit.dto.BookDto;
import com.bookshelf.unit.entity.BookEntity;
import com.bookshelf.unit.service.BookRegisterService;
import com.bookshelf.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private BookEntity bookEntity;

    private String bookJsonStringRequest;

    private String bookJsonStringResponse;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookRegisterController).build();

        bookEntity = new BookEntity(0L, "Effective Java", "IT", false);

        BookDto bookDto = new BookDto(0L, "Effective Java", "IT");
        bookJsonStringRequest = objectMapper.writeValueAsString(bookDto);
        bookJsonStringResponse = objectMapper.writeValueAsString(bookEntity);
    }

    @Test
    public void create_正常系_本の登録_201のレスポンス() throws Exception {

        when(bookRegisterService.registerBook(any())).thenReturn(bookEntity);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJsonStringRequest))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/book/" + bookEntity.getId()));
    }

    @Test
    public void get_正常系_本の取得_200のレスポンス() throws Exception {

        when(bookRegisterService.findBookById(0L)).thenReturn(bookEntity);

        mockMvc.perform(get("/book/0"))
                .andExpect(content().json(bookJsonStringResponse))
                .andExpect(status().isOk());
    }

    @Test
    public void get_異常_本が見つからなかった_404() throws Exception {

        when(bookRegisterService.findBookById(0L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/book/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_正常_本の削除が正常に行われた() throws Exception {

        mockMvc.perform(delete("/book/0"))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void put_正常_本のアクティビティの変更() throws Exception {

        when(bookRegisterService.updateBookActivity(0L)).thenReturn(bookEntity);

        mockMvc.perform(put("/book/activity/0"))
                .andExpect(content().json(bookJsonStringResponse))
                .andExpect(status().isOk());

    }


    @Test
    public void put_異常_本が見つからなかった_404() throws Exception {

        when(bookRegisterService.updateBookActivity(0L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(put("/book/activity/0"))
                .andExpect(status().isNotFound());
    }
}