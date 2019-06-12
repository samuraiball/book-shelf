package com.bookshelf.model.service.Impl;

import com.bookshelf.model.entity.UserEntity;
import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.model.repository.UserRepository;
import com.bookshelf.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		UserEntity findUser = userRepository.findUserByEmail(userEntity.getEmail());
		if (findUser != null) throw new UserAlreadyExistException("user already exist");
		userEntity.setId(UUID.randomUUID().toString());
		userEntity.setRole("ROLE_USER");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return userRepository.saveAndFlush(userEntity);
	}
}
