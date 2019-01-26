package com.bookshelf.dto;


import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDtoTest {


    private Validator validator;
    private UserDto userDto;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        userDto = new UserDto("user@gmail.com", "user", "password");
    }


    @Test
    public void 正常_バリデーション() {
        Set<ConstraintViolation<UserDto>> results = validator.validate(userDto);
        assertThat(results, is(empty()));

    }


    @Test
    public void 異常_無記名() {
        userDto.setUsername("");
        Set<ConstraintViolation<UserDto>> results = validator.validate(userDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class));
        });
    }

    @Test
    public void 異常_メールアドレスなし() {
        userDto.setEmail(null);
        Set<ConstraintViolation<UserDto>> results = validator.validate(userDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class));
        });
    }


    @Test
    public void 異常_メールアドレスの形式になっていない() {
       userDto.setEmail("user@7r32080832094823 ");
        Set<ConstraintViolation<UserDto>> results = validator.validate(userDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(Email.class));
        });
    }


    @Test
    public void 異常_パスワードなし() {
        userDto.setEmail("");
        Set<ConstraintViolation<UserDto>> results = validator.validate(userDto);
        assertThat(results.size(), is(1));
        results.forEach(result -> {
            assertThat(result.getConstraintDescriptor().getAnnotation(), instanceOf(NotEmpty.class));
        });
    }

}