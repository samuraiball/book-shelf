package com.bookshelf.controller;

import com.bookshelf.model.dto.BookDto;
import com.bookshelf.model.entity.BookEntity;
import com.bookshelf.model.service.BookRegisterService;
import com.bookshelf.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
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

    private String BOOK_ID = "550e8400-e29b-41d4-a716-446655440000";

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookRegisterController).build();

        BookDto bookDto = new BookDto("Effective Java", "http://localhost", "IT", "description");
        bookEntity = new BookEntity(BOOK_ID, "Effective Java","description", "IT", true);

        bookJsonStringRequest = objectMapper.writeValueAsString(bookDto);
        bookJsonStringResponse = objectMapper.writeValueAsString(bookEntity);
    }

    @Test(expected = AuthenticationException.class)
    public void 認証前() throws Exception{
        when(bookRegisterService.findBookById(BOOK_ID)).thenReturn(bookEntity);
        mockMvc.perform(get("/book/" + BOOK_ID))
                .andExpect(content().json(bookJsonStringResponse))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "")
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

        when(bookRegisterService.findBookById(BOOK_ID)).thenReturn(bookEntity);

        mockMvc.perform(get("/book/" + BOOK_ID))
                .andExpect(content().json(bookJsonStringResponse))
                .andExpect(status().isOk());
    }

    @Test
    public void get_異常_本が見つからなかった_404() throws Exception {

        when(bookRegisterService.findBookById(BOOK_ID)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/book/" + BOOK_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_正常_本の削除が正常に行われた() throws Exception {
        doNothing().when(bookRegisterService).deleteBook(BOOK_ID);
        mockMvc.perform(delete("/book/" + BOOK_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_異常_削除対象の本が見つからなかった() throws Exception {
        doThrow(ResourceNotFoundException.class).when(bookRegisterService).deleteBook(BOOK_ID);
        mockMvc.perform(delete("/book/" + BOOK_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void put_正常_本のアクティビティの変更() throws Exception {

        when(bookRegisterService.updateBookActivity(BOOK_ID)).thenReturn(bookEntity);

        mockMvc.perform(put("/book/activity/" + BOOK_ID))
                .andExpect(content().json(bookJsonStringResponse))
                .andExpect(status().isOk());

    }


    @Test
    public void put_異常_本が見つからなかった_404() throws Exception {

        when(bookRegisterService.updateBookActivity(BOOK_ID)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(put("/book/activity/" + BOOK_ID))
                .andExpect(status().isNotFound());
    }
}