package com.bookshelf.unit.dto;


import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class BookDtoTest {

    private Validator validator;
    private BookDto bookDto;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        bookDto = new  BookDto(0L,"Effective Java", "IT");
    }

    @Test
    public void 正常系() throws Exception {
        Set<ConstraintViolation<BookDto>> result = validator.validate(bookDto);
        assertThat(result, is(empty()));
    }

    @Test
    public void 異常_title_空文字() throws Exception {
        bookDto.setTitle("");
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> { assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class)); });
    }

    @Test
    public void 異常_genre_空文字() throws Exception {
        bookDto.setTitle("");
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> { assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class)); });
    }
}