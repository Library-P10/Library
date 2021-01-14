package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Customer;
import com.web.library.weblibrary.proxies.WaitingListProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class WaitingListController {

    // ----- Injections des dépendances ----- //
    private final WaitingListProxy waitingListProxy;

    @Autowired
    public WaitingListController(WaitingListProxy waitingListProxy){
        this.waitingListProxy = waitingListProxy;
    }

    // ----- ----- //

    /**
     * Ajout d'une nouvelle réservation dans la liste d'attente
     * @param idBook
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/waitingList/insert/{idBook}", method = RequestMethod.GET)
    public String insertWaitingList (@PathVariable("idBook") Long idBook,
                                     HttpSession httpSession){

            if(httpSession.getAttribute("customer") != null) {
                Customer customer = (Customer) httpSession.getAttribute("customer");
                waitingListProxy.insertWaitingList(customer.getId(), idBook);
            }
            return "redirect:/books";
    }

    /**
     * Supprime la réservation de la liste d'attente
     * @param idWaitingList
     */
    @RequestMapping(value = "/waitingList/delete/{idWaitingList}", method = RequestMethod.GET)
    public String deleteWaitingList(@PathVariable("idWaitingList") Long idWaitingList){
        waitingListProxy.deleteWaitingList(idWaitingList);
        return "redirect:/books";
    }
}
