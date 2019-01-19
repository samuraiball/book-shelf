package com.bookshelf.unit.service;

import com.bookshelf.unit.entity.BookEntity;

public interface BookRegisterService {
    BookEntity registerBook(BookEntity bookEntity);
    BookEntity findBookById(long bookId);
    BookEntity updateBookActivity(long BookId);
}
