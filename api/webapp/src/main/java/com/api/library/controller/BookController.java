package com.api.library.controller;

import com.api.library.dto.BookDto;
import com.api.library.mapper.BookMapper;
import com.api.library.model.Book;
import com.api.library.service.contract.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private BookService bookService;

    // -----------------------------------------------------  //

    @GetMapping(value = "books")
    public List<BookDto> displayBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping(value = "books/categorie")
    public List<BookDto> displayBookByCategorie(@RequestParam(name = "categorie") String categorie){
        return bookService.getBookByCategorie(categorie);
    }

    /**
     * Récupère les books selon la catégorie
     * @param saisie
     * @return
     */
    @PostMapping(value = "books/search")
    public List<BookDto> displayBookBySearch(@RequestParam(name = "saisie")String saisie){
        return bookService.getBookBySearch(saisie);
    }

    @GetMapping(value = "book/{idBook}")
    public BookDto getBookById(@PathVariable("idBook") Long id){

        BookDto bookDto = bookService.getBookById(id);

        return bookDto;
    }
}
