package com.bookshelf.function;

import com.bookshelf.model.entity.UserEntity;
import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserCreateFuncTest {

    @Autowired
    UserService userService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private UserEntity userEntity;

    private String ROW_PASSWORD = "password";

    @Before
    public void setUp() {
        userEntity = new UserEntity(null, "mohe@example.com",
                ROW_PASSWORD, "mohe heno", null);
    }

    @Test
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 正常_アプリ側で生成するデータが挿入されている() {
        userService.createUser(userEntity);
        Map result = jdbcTemplate.queryForMap("SELECT * FROM users WHERE email = ?", userEntity.getEmail());

        //パスワードがそのまま保存されていない。
        assertThat(result.get("password"), is(not(ROW_PASSWORD)));
        //IDが生成されて挿入されている。
        assertThat(result.get("id"), is(notNullValue()));
        //デフォルトのロール（USER）が挿入されている。
        assertThat(result.get("role"), is("ROLE_USER"));
    }

    @Test(expected = UserAlreadyExistException.class)
    @Sql("classpath:META-INF/sql/init-tables.sql")
    public void 異常_存在するユーザを登録しようとする(){
        userService.createUser(userEntity);
        userService.createUser(userEntity);
    }

}
