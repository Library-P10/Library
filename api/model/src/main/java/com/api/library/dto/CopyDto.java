package com.api.library.dto;

import com.api.library.model.Book;

public class CopyDto {

    private Long id;
    private String format;
    private String status;
    private LibraryDto library;
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(final LibraryDto library) {
        this.library = library;
    }
}
