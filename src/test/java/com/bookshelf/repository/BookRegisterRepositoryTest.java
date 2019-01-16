package com.bookshelf.repository;

import com.bookshelf.entity.BookEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterRepositoryTest {

    @Autowired
    BookRegisterRepository bookRegisterRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BookEntity bookEntity;

    @Before
    public void setUp(){
        bookEntity = new BookEntity();
        bookEntity.setName("Effective Java");
        bookEntity.setGenre("IT");
    }

    @Test
    @Sql("classpath:META-INF/sql/delete-data.sql")
    public void 正常(){
        BookEntity book = bookRegisterRepository.saveAndFlush(bookEntity);

        Map result =  jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = " + book.getId());
        System.out.println("result = " + result);

        assertThat(result.get("id"),is(0));
        assertThat(result.get("name"),is("Effective Java"));
        assertThat(result.get("genre"),is("IT"));

    }
}