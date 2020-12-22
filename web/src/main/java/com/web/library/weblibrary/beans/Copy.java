package com.web.library.weblibrary.beans;

public class Copy {

    private Long id;
    private String format;
    private String status;
    private Book book;
    private Library library;

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", format='" + format + '\'' +
                ", status='" + status + '\'' +
                ", book=" + book +
                ", library=" + library +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public Copy() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(final Library library) {
        this.library = library;
    }
}
