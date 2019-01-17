package com.bookshelf.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class BookEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String title;

    @Column
    String genre;

    public BookEntity(){
    }

    public BookEntity(long id, String title, String genre){
        this.id = id;
        this.title = title;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
