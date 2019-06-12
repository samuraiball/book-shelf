package com.bookshelf.model.service;

import com.bookshelf.model.entity.BookEntity;

public interface BookRegisterService {
    BookEntity registerBook(BookEntity bookEntity);
    BookEntity findBookById(String bookId);
    BookEntity updateBookActivity(String bookId);
    void deleteBook(String bookId);
}
