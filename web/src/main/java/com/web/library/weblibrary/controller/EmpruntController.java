package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Emprunt;
import com.web.library.weblibrary.proxies.CopyProxy;
import com.web.library.weblibrary.proxies.EmpruntProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmpruntController {

    // ----- Injections des dépendances ----- //

    @Autowired
    private EmpruntProxy empruntProxy;

    @Autowired
    private CopyProxy copyProxy;

    // ----- ----- //

    /**
     * Affiche les prêts de l'utilisateur
     *
     * @param httpSession
     * @param idSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/emprunt/{idSession}", method = RequestMethod.GET)
    public String displayEmprunt(HttpSession httpSession,
                                 @PathVariable("idSession") Long idSession,
                                 Model model) {

        List<Emprunt> emprunts = empruntProxy.listEmpruntByCustomer(idSession);

        model.addAttribute("emprunt", emprunts);

        return "emprunts";
    }

    /**
     * Prolonge le prêt
     * @param httpSession
     * @param idEmprunt
     * @return
     */
    @RequestMapping(value = "/emprunt/extended/{idEmprunt}", method = RequestMethod.GET)
    public String extendEmprunt(HttpSession httpSession,
                                @PathVariable("idEmprunt") Long idEmprunt) {

        empruntProxy.ExtendLoan(idEmprunt);

        return "redirect:/books";
    }

    /**
     * Méthode qui formatte la date de retour prévue
     * @param date
     * @return
     */
    private String formatDateToMail(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(date);
    }
}
