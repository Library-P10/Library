package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Customer;
import com.web.library.weblibrary.proxies.WaitingListProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/waitingList/insert/{idBook}", method = RequestMethod.GET)
    public String insertWaitingList (@PathVariable("idBook") Long idBook,
                                     HttpSession httpSession){

        if(httpSession.getAttribute("customer") !=null){
            Customer customer = (Customer) httpSession.getAttribute("customer");
            waitingListProxy.insertWaitingList(customer.getId(), idBook);
        }

        return "redirect:/books";
    }
}
