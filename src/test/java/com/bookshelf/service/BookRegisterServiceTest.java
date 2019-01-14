package com.bookshelf.service;


import com.bookshelf.entity.BookEntity;
import com.bookshelf.repository.BookRegisterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void setUp(){

        when(bookResistorRepository.insert(bookEntity)).thenReturn(1);

    }

    @Test
    public void 正常(){
        bookResistorService.resistorBook(bookEntity);
        verify(bookResistorRepository,times(1)).insert(bookEntity);
    }
}