package com.bookshelf.controller;

import com.bookshelf.dto.BookDto;
import com.bookshelf.entity.BookEntity;
import com.bookshelf.service.BookRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/book")
public class BookRegisterController {

    @Autowired
    private BookRegisterService bookRegisterService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity registerBook(@RequestBody @Valid BookDto bookDto, UriComponentsBuilder uriBuilder) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookDto.getTitle());
        bookEntity.setGenre(bookDto.getGenre());

        BookEntity book = bookRegisterService.registerBook(bookEntity);
        URI location = uriBuilder.path("/book/{id}")
                .buildAndExpand(book.getId())
                .toUri();

        // レスポンスのHTTPヘッダー
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{bookId}")
    public ResponseEntity getBook(@PathVariable long bookId){
        return new ResponseEntity(HttpStatus.OK);
    }
}
