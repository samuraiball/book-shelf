package com.bookshelf.entity;

import org.seasar.doma.*;

@Entity
@Table(name = "books")
public class BookEntity {


    @Id
    int id;

    String name;

    String genre;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
