package com.bookshelf.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String Massage){
        super(Massage);
    }
}
