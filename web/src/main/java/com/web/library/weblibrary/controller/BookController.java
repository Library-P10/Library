package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Book;
import com.web.library.weblibrary.beans.Customer;
import com.web.library.weblibrary.proxies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    // ----- Injections des dépendances ----- //

    private final WaitingListProxy waitingListProxy;
    private final CopyProxy copyProxy;
    private final CategorieProxy categorieProxy;
    private final BookProxy bookProxy;
    private final CustomerProxy customerProxy;
    private final EmpruntProxy empruntProxy;

    @Autowired
    public BookController(WaitingListProxy waitingListProxy,
                          CopyProxy copyProxy,
                          CategorieProxy categorieProxy,
                          BookProxy bookProxy,
                          CustomerProxy customerProxy,
                          EmpruntProxy empruntProxy){
        this.waitingListProxy = waitingListProxy;
        this.copyProxy = copyProxy;
        this.categorieProxy = categorieProxy;
        this.bookProxy = bookProxy;
        this.customerProxy = customerProxy;
        this.empruntProxy = empruntProxy;
    }

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
     * Affichee les livres par catégorie
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
     * Affiche les livres selon la saisie de l'utilisateur dans la barre de recherche
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

    /**
     * Affiche le book
     * @param idBook
     * @param model
     * @return
     */
    @RequestMapping(value = "/book/{idBook}", method = RequestMethod.GET)
    public String getBookById(@PathVariable("idBook") Long idBook,
                              Model model,
                              HttpSession httpSession){

        if(httpSession.getAttribute("customer") !=null){
            Customer customer = (Customer) httpSession.getAttribute("customer");
            model.addAttribute("insertAvailable",
                    waitingListProxy.insertAvailable(idBook, customer.getId()));
            model.addAttribute("nextReturn", empruntProxy.getNextReturn(idBook));
            model.addAttribute("numberCustomerWaiting",waitingListProxy.getNumberCustomerInWaitingList(idBook));
        }

        model.addAttribute("book",bookProxy.getBookById(idBook));
        model.addAttribute("copy",copyProxy.getCopyByIdBook(idBook));

        return "/book";
    }
}
