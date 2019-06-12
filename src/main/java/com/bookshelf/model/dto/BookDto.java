package com.bookshelf.model.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Book dto
 */
public class BookDto implements Serializable {

	@NotEmpty
	@Size(max = 100)
	private String title;

	@Pattern(regexp = "^(https?|http)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$",
			message = "dose not match URL Pattern")
	private String url;


	@Size(max = 300)
	private String description;

	@Size(max = 50)
	private String genre;

	BookDto() {
	}

	public BookDto(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}

	public BookDto(String title, String url, String genre, String description) {
		this.url = url;
		this.title = title;
		this.genre = genre;
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}


	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
