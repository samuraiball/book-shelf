package com.bookshelf.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class BookEntity implements Serializable {


    @Id
    private String id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private boolean isActive;

    public BookEntity() {
    }

    public BookEntity(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public BookEntity(String id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public BookEntity(String id, String title, String genre, boolean isActive) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.isActive = isActive;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggleActive() {
        this.isActive = !this.isActive;
    }

    @PrePersist
    private void setActive() {
        this.isActive = true;
    }

}
