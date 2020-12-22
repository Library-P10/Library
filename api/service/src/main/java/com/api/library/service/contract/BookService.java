package com.api.library.service.contract;

import com.api.library.dto.BookDto;
import com.api.library.model.Book;

import java.util.List;

public interface BookService {

    /**
     *
     * @return  La liste de tout les livres
     */
    List<BookDto> getAllBooks();

    /**
     *
     * @param id du livre
     * @return le livre selon l'id
     */
    BookDto getBookById(Long id);

    /**
     *
     * @param book
     * @return  Ajoute un livre en base
     */
    Book addBook(Book book);

    /**
     *
     * @return  les livres selon la catégorie
     */
    List<BookDto> getBookByCategorie(String categorie);

    /**
     *
     * @param saisie
     * @return la listes des livres par rapport à la recherche
     */
    List<BookDto> getBookBySearch(String saisie);

}
