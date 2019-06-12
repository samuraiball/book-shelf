package com.bookshelf.function;

import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.model.entity.BookEntity;
import com.bookshelf.model.service.BookRegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRegisterFuncTest {

    @Autowired
    BookRegisterService bookRegisterService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BookEntity bookEntity;

    @Before
    public void setUp() {
        bookEntity = new BookEntity("Effective Java", "IT");
    }

    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_アクティビティフラグ更新() {

        BookEntity registerBook = bookRegisterService.registerBook(bookEntity);

        Map result = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", registerBook.getId());

        assertThat(result.size() > 0, is(true));
        assertThat(result.get("is_active"), is(true));

        //アクティベイト⇒ディアクティベイト
        bookRegisterService.updateBookActivity(registerBook.getId());
        Map updatedResult = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", registerBook.getId());
        assertThat(updatedResult.get("is_active"), is(false));

        //ディアクティベイト⇒アクティベイト
        bookRegisterService.updateBookActivity(registerBook.getId());
        updatedResult = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", registerBook.getId());
        assertThat(updatedResult.get("is_active"), is(true));
    }

    @Test(expected = ResourceNotFoundException.class)
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 異常_アクティビティフラグの更新_更新対象が見つからなかった() {
        bookRegisterService.updateBookActivity("no_data");
    }


    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_削除() {
        BookEntity deleteBook = bookRegisterService.registerBook(bookEntity);

        Map result = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", deleteBook.getId());
        assertThat(result.size() > 0, is(true));
        assertThat(result.get("is_deleted"), is(false));

        bookRegisterService.deleteBook(deleteBook.getId());

        Map updatedResult = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", deleteBook.getId());
        assertThat(updatedResult.get("is_deleted"), is(true));

    }


    @Test(expected = ResourceNotFoundException.class)
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_削除_削除対象がすでに削除されている() {
        BookEntity deleteBook = bookRegisterService.registerBook(bookEntity);

        Map result = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = ?", deleteBook.getId());
        assertThat(result.size() > 0, is(true));
        assertThat(result.get("is_deleted"), is(false));

        bookRegisterService.deleteBook(deleteBook.getId());
        bookRegisterService.deleteBook(deleteBook.getId());
    }
}
