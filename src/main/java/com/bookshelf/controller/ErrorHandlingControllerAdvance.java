package com.bookshelf.controller;

import com.bookshelf.exception.UserAlreadyExistException;
import com.bookshelf.model.dto.Builder.ErrorResourceBuilder;
import com.bookshelf.model.dto.ErrorResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlingControllerAdvance extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserAlreadyExistException.class)
	protected ResponseEntity<Object> handlingUserAlredyExistExveption(
			UserAlreadyExistException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		ErrorResource er = new ErrorResourceBuilder()
				.withMessage("User already exist")
				.withTimestamp(LocalDateTime.now())
				.createErrorResource();
		return this.handleExceptionInternal(ex, er, headers, status, request);
	}

}
