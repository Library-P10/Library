package com.api.library.service.impl;

import com.api.library.dto.BookDto;
import com.api.library.mapper.BookMapper;
import com.api.library.model.Book;
import com.api.library.repository.BookRepository;
import com.api.library.service.contract.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    // ----------------- Injections des dépendances ----------------- //
    @Autowired
    private BookRepository bookRepository;

    // -----------------------------------------------------  //

    /**
     *
     * @return la liste des books
     */
    @Override
    public List<BookDto> getAllBooks() {
        return BookMapper.INSTANCE.map(bookRepository.getAllBooks());
    }

    /**
     *
     * @param id du livre
     * @return la liste des books par id
     */
    @Override
    public BookDto getBookById(final Long id) {
        return BookMapper.INSTANCE.bookToBookDto(bookRepository.getBookById(id));
    }

    /**
     *
     * @param book
     * @return Ajoute un book
     */
    @Override
    public Book addBook(final Book book) {
        return null;
    }

    /**
     *
     * @param categorie
     * @return la liste des books selon la catégorie
     */
    @Override
    public List<BookDto> getBookByCategorie(String categorie) {



        return BookMapper.INSTANCE.map(bookRepository.getBookByCategorie(categorie));
    }

    /**
     *
     * @param saisie
     * @return la liste des books selon la saisie de recherche
     */
    @Override
    public List<BookDto> getBookBySearch(String saisie) {
        saisie = saisie.replaceAll("\\s","");
        saisie = saisie+"%";

        return BookMapper.INSTANCE.map(bookRepository.getBookBySearch(saisie));
    }

}
