package com.bookshelf.model.dto;


import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
        bookDto = new BookDto("Effective Java", "http://localhost", "IT", "description");

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
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class));
        });
    }

    @Test
    public void 異常_title_100文字以上() throws Exception {
        bookDto.setTitle(buildString(101));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(Size.class));
        });
    }

    @Test
    public void 正常_title_100文字() throws Exception {
        bookDto.setTitle(buildString(100));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(0));
    }

    @Test
    public void 異常_genre_文字数_50文字以上() {
        bookDto.setGenre(buildString(51));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(Size.class));
        });
    }

    @Test
    public void 正常_genre_文字数_50文字() {
        bookDto.setGenre(buildString(50));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(0));
    }

    @Test
    public void 異常_description_文字数_300文字以上(){
        bookDto.setDescription(buildString(301));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(Size.class));
        });
    }


    @Test
    public void 正常_description_文字数_300文字() {
        bookDto.setDescription(buildString(300));
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(0));
    }

    @Test
    public void 異常_URLのパターンが違う() throws Exception {
        bookDto.setUrl("a");
        Set<ConstraintViolation<BookDto>> results = validator.validate(bookDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(Pattern.class));
        });
    }

    private String buildString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) builder.append("a");
        return builder.toString();
    }
}