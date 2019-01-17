package com.bookshelf.service;

import com.bookshelf.entity.BookEntity;
import com.bookshelf.repository.BookRegisterRepository;
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


}
