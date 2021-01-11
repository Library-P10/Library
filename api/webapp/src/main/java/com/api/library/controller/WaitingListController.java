package com.api.library.controller;

import com.api.library.dto.WaitingListDto;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * Récupère les réservations dont la dateRecoveryLimit est dépassée
     * @return
     */
    @GetMapping(value = "waitingList/retardGet")
    public List<WaitingListDto> getDateRecoveryLimit(HttpServletRequest httpServletRequest){
        return waitingListService.getWaitingListByDateRecoveryLimitExceeded();
    }

    /**
     * Supprime la réservation pas récupérer dans les 48h et envoi un mail au suivant
     * @param waitingListDtos
     */
    @PostMapping(value = "waitingList/changeCustomer")
    public void changerCustomerInWaitingList(@RequestBody List<WaitingListDto> waitingListDtos){
    // 1 - Supprimer la réservation dont le temps de récupération à changer
    // 2 - Envoyer un mail au second

        // Pour chaque liste de réservation
        for (WaitingListDto waitingListDto : waitingListDtos){

            //On supprime la réservation de la date dépassée
            waitingListService.deleteWaitingList(waitingListDto.getId());

            // On envoi un mail au suivant si il existe sinon le livre est disponible
            waitingListService.sendMailForNextCustomer(waitingListDto.getBook().getId());
        }
    }

}
