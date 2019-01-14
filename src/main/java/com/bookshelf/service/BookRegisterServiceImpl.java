package com.bookshelf.service;

import com.bookshelf.entity.BookEntity;
import com.bookshelf.repository.BookRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRegisterServiceImpl implements BookRegisterService {

    @Autowired
    BookRegisterRepository bookResistorRepository;

    @Override
    public int resistorBook(BookEntity bookEntity){
       return bookResistorRepository.insert(bookEntity);
    }

}
