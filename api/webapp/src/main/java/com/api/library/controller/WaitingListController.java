package com.api.library.controller;

import com.api.library.dto.WaitingListDto;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WaitingListController {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListService waitingListService;

    @Autowired
    public WaitingListController(WaitingListService waitingListService){
        this.waitingListService = waitingListService;
    }

    // -----------------------------------------------------  //

    /**
     * Récupère la liste d'attente d'un livre
     * @param idBook
     * @return
     */
    @GetMapping(value = "waitingList/{idBook}")
    public List<WaitingListDto> displayWaitingList(@PathVariable("idBook") Long idBook){
        return waitingListService.getWaitingListByIdBook(idBook);
    }

    /**
     * Récupère l'occurence d'un livre dans une liste d'attente
     * @param idBook
     * @return
     */
    @GetMapping(value = "waitingList/number/{idBook}")
    public Integer getNumberBookInWaitingList(@PathVariable("idBook") Long idBook){
        return waitingListService.getNumberBookInWaitingList(idBook);
    }

    /**
     * Supprime la réservation pas récupérer dans les 48h et envoi un mail au suivant
     * @param idWaiting
     */
    @PostMapping(value = "waitingList/changeCustomer/{idWaiting}")
    public void changerCustomerInWaitingList(@PathVariable("idWaitingList")Long idWaiting){
    // 1 - Supprimer la réservation dont le temps de récupération à changer
    // 2 - Envoyer un mail au second
        // On récupère la réservation à annuler
        WaitingListDto waitingListDto = waitingListService.getWaitingListById(idWaiting);

        //On supprime la réservation de la date dépassée
        waitingListService.deleteWaitingList(idWaiting);

        // On envoi un mail au suivant si il existe sinon le livre est disponible
        waitingListService.sendMailForNextCustomer(waitingListDto.getBook().getId());
    }

}
