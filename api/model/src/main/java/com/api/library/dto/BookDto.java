package com.api.library.dto;

import com.api.library.model.Book;

import java.util.Date;
import java.util.List;

public class BookDto {

    private Long id;
    private String title;
    private Date pubDate;
    private int page;
    private String synopsis;
    private String cover;
    private CategorieDto categorie;
    private AuthorDto author;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(final Date pubDate) {
        this.pubDate = pubDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(final String cover) {
        this.cover = cover;
    }

    public CategorieDto getCategorie() {
        return categorie;
    }

    public void setCategorie(final CategorieDto categorie) {
        this.categorie = categorie;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(final AuthorDto author) {
        this.author = author;
    }

}
