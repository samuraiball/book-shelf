package com.bookshelf.service;


import com.bookshelf.entity.BookEntity;
import com.bookshelf.repository.BookRegisterRepository;
import com.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterServiceTest {

    @MockBean
    BookRegisterRepository bookResistorRepository;

    @Autowired
    BookRegisterService bookResistorService;

    private BookEntity bookEntity = new BookEntity();

    @Before
    public void setUp() {

    }

    @Test
    public void 正常_insert_book() {
        when(bookResistorRepository.saveAndFlush(bookEntity)).thenReturn(bookEntity);
        bookResistorService.registerBook(bookEntity);
        verify(bookResistorRepository, times(1)).saveAndFlush(bookEntity);
    }

    @Test
    public void 正常_find_book_by_id() {
        when(bookResistorRepository.findById(any())).thenReturn(any());
        bookResistorService.findBookById(0L);
        verify(bookResistorRepository, times(1)).findById(0L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void 異常_find_book_by_id_本が見つからなかった(){
        when(bookResistorRepository.findById(any())).thenReturn(null);
        bookResistorService.findBookById(0L);
    }
}