package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Book;
import com.web.library.weblibrary.proxies.BookProxy;
import com.web.library.weblibrary.proxies.CategorieProxy;
import com.web.library.weblibrary.proxies.CopyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    // ----- Injections des dépendances ----- //

    @Autowired
    private BookProxy bookProxy;

    @Autowired
    private CopyProxy copyProxy;

    @Autowired
    private CategorieProxy categorieProxy;

    // ----- ----- //

    /**
     * Affiche tout les livres
     * @param model
     * @return
     */
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public String getBooks(Model model){

        List<Book> books = bookProxy.listBooks();

        model.addAttribute("books", books);
        model.addAttribute("categorie", categorieProxy.getCategorie());

        return "books";
    }

    /**
     * Affie les livres par catégorie
     * @param model
     * @param categorie
     * @return
     */
    @RequestMapping(value = "/books/categorie",method = RequestMethod.POST)
    public String getBookByCategorie(Model model,
                                     @RequestParam(name="categorie", defaultValue = "", required = false) String categorie){

        if (categorie.equals("")){
            return "redirect:/books";
        }

        List<Book> books = bookProxy.listBooksByCategorie(categorie);

        model.addAttribute("books", books);
        model.addAttribute("categorie",categorieProxy.getCategorie());

        return "books";
    }

    /**
     * Affice les livres selon la saisie de l'utilisateur dans la barre de recherche
     * @param saisie
     * @param model
     * @return
     */
    @RequestMapping(value = "/books/search", method = RequestMethod.POST)
    public String getBooksBySearch(@RequestParam(name = "saisie", defaultValue = "", required = false) String saisie,
                                   Model model){

        model.addAttribute("books", bookProxy.getBookBySearch(saisie));
        model.addAttribute("categorie", categorieProxy.getCategorie());

        return "/books";
    }

    @RequestMapping(value = "/book/{idBook}", method = RequestMethod.GET)
    public String getBookById(@PathVariable("idBook") Long idBook,
                              Model model){

        model.addAttribute("book",bookProxy.getBookById(idBook));
        model.addAttribute("copy",copyProxy.getCopyByIdBook(idBook));

        return "/book";
    }
}
