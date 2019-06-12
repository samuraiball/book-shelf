package com.bookshelf.model.repository;

import com.bookshelf.model.entity.BookEntity;
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

    private String BOOK_ID = "550e8400-e29b-41d4-a716-446655440000";

    private String REGISTER_BOOK_ID = "resis8400-e29b-41d4-a716-446655440000";

    @Before
    public void setUp() {
        bookEntity = new BookEntity(REGISTER_BOOK_ID,"Effective Java","description","IT");
    }

    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_Insert_Book() {

        BookEntity book = bookRegisterRepository.saveAndFlush(bookEntity);

        Map result = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", book.getId());

        assertThat(result.get("id"), is(REGISTER_BOOK_ID));
        assertThat(result.get("title"), is("Effective Java"));
        assertThat(result.get("genre"), is("IT"));
        assertThat(result.get("description"), is("description"));
        assertThat(result.get("is_active"), is(true));

    }

    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_find_book_by_id() {

        BookEntity result = bookRegisterRepository.findBookById(BOOK_ID);

        assertThat(result.getId(), is(BOOK_ID));
        assertThat(result.getTitle(), is("WebAPI: The Good Part"));
        assertThat(result.getDescription(), is("description"));
        assertThat(result.getGenre(), is("IT"));
        assertThat(result.isActive(), is(true));
        assertThat(result.isDeleted(), is(false));
    }
}