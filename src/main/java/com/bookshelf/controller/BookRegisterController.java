package com.bookshelf.controller;

import com.bookshelf.dto.BookDto;
import com.bookshelf.entity.BookEntity;
import com.bookshelf.service.BookRegisterService;
import com.bookshelf.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/book")
public class BookRegisterController {

    @Autowired
    private BookRegisterService bookRegisterService;


    @PostMapping
    public ResponseEntity registerBook(@RequestBody @Valid BookDto bookDto, UriComponentsBuilder uriBuilder) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setGenre(bookDto.getGenre());

        BookEntity book = bookRegisterService.registerBook(bookEntity);
        URI location = uriBuilder.path("/book/{id}")
                .buildAndExpand(book.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{bookId}")
    public ResponseEntity getBook(@PathVariable String bookId) {
        try {
            return new ResponseEntity<>(bookRegisterService.findBookById(bookId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found", e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) {
        try {
            bookRegisterService.deleteBook(bookId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found", e);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/activity/{bookId}")
    public ResponseEntity changeActivity(@PathVariable String bookId) {
        try {
            return new ResponseEntity<>(bookRegisterService.updateBookActivity(bookId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found", e);
        }
    }
}
