package com.web.library.weblibrary.beans;

public class CopyByBook {

    private int numberCopy;
    private String format;
    private Library library;
    private Book book;

    public int getNumberCopy() {
        return numberCopy;
    }

    public void setNumberCopy(final int numberCopy) {
        this.numberCopy = numberCopy;
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

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }
}
