package com.bookshelf.controller;

import com.bookshelf.dto.UserDto;
import com.bookshelf.dto.UserResponseDto;
import com.bookshelf.entity.UserEntity;
import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.repository.UserRepository;
import com.bookshelf.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody @Valid UserDto userDto) {

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(userDto, userEntity);

        UserEntity createdUserEntity = null;
        try {
            createdUserEntity = userService.createUser(userEntity);
        } catch (UserAlreadyExistException e) {
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "User already exist", e);
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        BeanUtils.copyProperties(createdUserEntity, userResponseDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
