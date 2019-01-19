package com.bookshelf.service;

import com.bookshelf.entity.BookEntity;

public interface BookRegisterService {
    BookEntity registerBook(BookEntity bookEntity);
    BookEntity findBookById(String bookId);
    BookEntity updateBookActivity(String bookId);
}
