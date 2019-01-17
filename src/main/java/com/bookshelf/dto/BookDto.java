package com.bookshelf.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Book dto
 */
public class BookDto implements Serializable {


    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String genre;

    public BookDto(){
    }

    public BookDto(Long id, String title, String genre){
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
