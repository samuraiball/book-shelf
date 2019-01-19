package com.bookshelf.service;

import com.bookshelf.entity.BookEntity;
import com.bookshelf.repository.BookRegisterRepository;
import com.bookshelf.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookRegisterServiceImpl implements BookRegisterService {

    @Autowired
    private BookRegisterRepository bookRegisterRepository;

    @Override
    public BookEntity registerBook(BookEntity bookEntity) {
        bookEntity.setId(UUID.randomUUID().toString());
        return bookRegisterRepository.saveAndFlush(bookEntity);
    }

    @Override
    public BookEntity findBookById(String bookId) {
       BookEntity bookEntity = findExistBook(bookId);
       if (bookEntity == null || bookEntity.isDeleted())throw new ResourceNotFoundException("Resource cannot find");
        return bookEntity;
    }

    @Override
    public BookEntity updateBookActivity(String bookId) {
        BookEntity bookEntity = findExistBook(bookId);
        bookEntity.toggleActive();
        BookEntity updatedBookEntity = bookRegisterRepository.saveAndFlush(bookEntity);
        return updatedBookEntity;
    }

    @Override
    public void deleteBook(String bookId) {
        BookEntity bookEntity = findExistBook(bookId);
        bookEntity.deleteBook();
        bookRegisterRepository.saveAndFlush(bookEntity);
    }


    private BookEntity findExistBook(String bookId){
        BookEntity bookEntity = bookRegisterRepository.findBookById(bookId);
        if (bookEntity == null || bookEntity.isDeleted())throw new ResourceNotFoundException("Resource cannot find");
        return bookEntity;
    }


}
