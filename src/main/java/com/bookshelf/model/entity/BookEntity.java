package com.bookshelf.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String description;

	@Column
	private String url;

	@Column
	private boolean isActive;

	@Column
	private boolean isDeleted;

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

	public BookEntity(String id, String title, String description, String genre) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.genre = genre;
	}

	public BookEntity(String id, String title, String description, String genre, boolean isActive) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return isActive;
	}

	@JsonIgnore
	public boolean isDeleted() {
		return isDeleted;
	}

	public void toggleActive() {
		this.isActive = !this.isActive;
	}

	public void deleteBook() {
		this.isDeleted = true;
	}

	@PrePersist
	private void setActive() {
		this.isActive = true;
		this.isDeleted = false;
	}

}
