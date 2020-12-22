package com.web.library.weblibrary.proxies;

import com.web.library.weblibrary.beans.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface BookProxy {

    @GetMapping(value = "/books")
    List<Book> listBooks();

    @GetMapping(value = "/book/{id}")
    Book getBookById(@PathVariable("id") Long id);

    @PostMapping(value = "/books/categorie")
    List<Book> listBooksByCategorie(@RequestParam("categorie") String categorie);

    @PostMapping(value = "/books/search")
    List<Book> getBookBySearch(@RequestParam(name = "saisie")String saisie);
}
