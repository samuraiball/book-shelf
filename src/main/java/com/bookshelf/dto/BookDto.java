package com.bookshelf.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Book dto
 */
public class BookDto implements Serializable {



    @NotEmpty
    private String title;

    @NotEmpty
    private String genre;

    public BookDto(){
    }

    public BookDto(String title, String genre){
        this.title = title;
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
