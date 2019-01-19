package com.bookshelf.fonction;

import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.unit.entity.BookEntity;
import com.bookshelf.unit.repository.BookRegisterRepository;
import com.bookshelf.unit.service.BookRegisterService;
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
public class UpdateActivityTest {

    @Autowired
    BookRegisterService bookRegisterService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    BookEntity bookEntity;

    @Before
    public void setUp() {
        bookEntity = new BookEntity("Effective Java","IT");
    }

    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_削除フラグの更新() {

        BookEntity registerBook = bookRegisterService.registerBook(bookEntity);
        Map result = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = " + registerBook.getId());

        assertThat(result.size() > 0, is(true));
        assertThat(result.get("is_active"), is(true));

        bookRegisterService.updateBookActivity(registerBook.getId());

        Map updatedResult = jdbcTemplate.queryForMap("SELECT * FROM books WHERE id = "+ registerBook.getId());
        assertThat(updatedResult.get("is_active"), is(false));
    }

    @Test(expected = ResourceNotFoundException.class)
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 異常_アクティビティフラグの更新_更新対象が見つからなかった() {
        bookRegisterService.updateBookActivity(-1);
    }


}
