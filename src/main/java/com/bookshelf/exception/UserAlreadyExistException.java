package com.bookshelf.exception;

import org.aspectj.bridge.Message;

public class UserAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UserAlreadyExistException(String massage){super(massage);}
}
