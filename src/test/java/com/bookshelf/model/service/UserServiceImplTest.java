package com.bookshelf.model.service;

import com.bookshelf.model.entity.UserEntity;
import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    UserEntity userEntity;

    @Before
    public void setUp(){
        userEntity = new UserEntity();
    }


    @Test(expected = UserAlreadyExistException.class)
    public void 異常_ユーザがすでに存在する(){
        when(userRepository.findUserByEmail(any())).thenReturn(userEntity);
        userService.createUser(userEntity);
    }
}