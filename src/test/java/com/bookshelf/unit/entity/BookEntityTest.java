package com.bookshelf.unit.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookEntityTest {

    BookEntity bookEntity;

    @Before
    public void setUp(){
        bookEntity = new BookEntity(0L, "Effective Java", "IT", true);

    }

    @Test
    public void 正常_アクティブのトグル(){
        bookEntity.toggleActive();
        assertThat(bookEntity.isActive(),is(false));
    }

}