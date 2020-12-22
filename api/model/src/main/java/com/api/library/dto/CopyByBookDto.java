package com.api.library.dto;

public class CopyByBookDto {
    private int numberCopy;
    private String format;
    private LibraryDto library;
    private BookDto book;

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

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(final LibraryDto library) {
        this.library = library;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(final BookDto book) {
        this.book = book;
    }
}
