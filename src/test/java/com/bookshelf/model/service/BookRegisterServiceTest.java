package com.bookshelf.model.service;


import com.bookshelf.model.entity.BookEntity;
import com.bookshelf.model.repository.BookRegisterRepository;
import com.bookshelf.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterServiceTest {

    @MockBean
    BookRegisterRepository bookResistorRepository;

    @Autowired
    BookRegisterService bookResistorService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BookEntity bookEntity = new BookEntity();

    private String BOOK_ID ="550e8400-e29b-41d4-a716-446655440000";

    @Test
    public void 正常_insert_book() {
        when(bookResistorRepository.saveAndFlush(bookEntity)).thenReturn(bookEntity);
        bookResistorService.registerBook(bookEntity);
        verify(bookResistorRepository, times(1)).saveAndFlush(bookEntity);
    }

    @Test
    public void 正常_find_book_by_id() {
        when(bookResistorRepository.findBookById(BOOK_ID)).thenReturn(bookEntity);
        bookResistorService.findBookById(BOOK_ID);
        verify(bookResistorRepository, times(1)).findBookById(BOOK_ID);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void 異常_find_book_by_id_本が見つからなかった() {
        when(bookResistorRepository.findBookById(any())).thenReturn(null);
        bookResistorService.findBookById(BOOK_ID);
    }


}