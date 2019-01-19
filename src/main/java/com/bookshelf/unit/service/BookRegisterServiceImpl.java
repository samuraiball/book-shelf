package com.bookshelf.unit.service;

import com.bookshelf.unit.entity.BookEntity;
import com.bookshelf.unit.repository.BookRegisterRepository;
import com.bookshelf.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterServiceImpl implements BookRegisterService {

    @Autowired
    private BookRegisterRepository bookRegisterRepository;

    @Override
    public BookEntity registerBook(BookEntity bookEntity) {
        return bookRegisterRepository.saveAndFlush(bookEntity);
    }

    @Override
    public BookEntity findBookById(long bookId) {
       BookEntity bookEntity = bookRegisterRepository.findById(bookId);
       if (bookEntity == null)throw new ResourceNotFoundException("Resource cannot find");
        return bookEntity;
    }

    @Override
    public BookEntity updateBookActivity(long bookId) {
        BookEntity bookEntity = bookRegisterRepository.findById(bookId);
        if (bookEntity == null)throw new ResourceNotFoundException("Resource cannot find");
        bookEntity.toggleActive();
        BookEntity UpdatedBookEntity = bookRegisterRepository.saveAndFlush(bookEntity);
        return UpdatedBookEntity;
    }


}
