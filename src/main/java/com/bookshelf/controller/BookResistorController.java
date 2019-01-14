package com.bookshelf.controller;

import com.bookshelf.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookResistorController {

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    public static void registerBook(@RequestBody @Valid BookDto bookDto){
    }
}
