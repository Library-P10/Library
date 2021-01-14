package com.api.library.controller;

import com.api.library.dto.EmpruntDto;
import com.api.library.dto.WaitingListDto;
import com.api.library.service.contract.CopyService;
import com.api.library.service.contract.EmpruntService;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class WaitingListController {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListService waitingListService;
    private final CopyService copyService;
    private final EmpruntService empruntService;

    @Autowired
    public WaitingListController(WaitingListService waitingListService,
                                 CopyService copyService,
                                 EmpruntService empruntService){
        this.waitingListService = waitingListService;
        this.copyService = copyService;
        this.empruntService = empruntService;
    }

    // -----------------------------------------------------  //

    /**
     * Récupère la liste d'attente d'un livre
     * @param idBook
     * @return
     */
    @GetMapping(value = "waitingList/{idBook}")
    public List<WaitingListDto> displayWaitingList(@PathVariable("idBook") Long idBook){
        return waitingListService.getWaitingListByIdBookByDateRequest(idBook);
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

    /**
     * Vérification de la possible mise en attente de réservation
     * Selon le nombre d'exemplaire de l'ouvrage
     * @param idBook
     * @return
     */
    @GetMapping(value = "waitingList/available")
    public Boolean newWaitingListAvailable (@RequestParam(name = "idBook") Long idBook,
                                            @RequestParam(name = "idCustomer") Long idCustomer){

        Boolean insertAvailable;
        int numberOfWaitinListAvailable;
        int numberBookInWaitingList;

        // Récupération du nombre d'exemplaires de l'ouvrage
        numberOfWaitinListAvailable = copyService.getNumberCopyByIdBook(idBook);
        // Récupération du nombre de réservation en attente
        numberBookInWaitingList = waitingListService.getNumberBookInWaitingList(idBook);

        insertAvailable = waitingListService.insertInWaitingListAvailable(numberBookInWaitingList,
                numberOfWaitinListAvailable, idBook, idCustomer);

        return insertAvailable;
    }

    /**
     * Ajout d'une réservation dans la liste d'attente
     * @param idBook
     * @param idCustomer
     * @return
     */
    @PostMapping(value = "waitingList/insert/{idBook}")
    public WaitingListDto insertNewWaitingList (@PathVariable("idBook") Long idBook,
                                                @RequestParam(name = "idCustomer") Long idCustomer){

        return waitingListService.insertWaitingList(idBook, idCustomer);
    }

    /**
     * Retourne le nombre de personne dans la liste d'attente
     * @param idBook
     * @return
     */
    @GetMapping(value = "waitingList/numberCustomer/{idBook}")
    public int getNumberCustomerInWaitingList(@PathVariable("idBook") Long idBook){
        return waitingListService.getNumberCustomerInWaitingList(idBook);
    }

    /**
     * Récupération de la liste d'attente de l'utilisateur
     * @param idCustomer
     * @return
     */
    @GetMapping(value = "waitingList/customer/{idCustomer}")
    public List<WaitingListDto> getWaitingListByCustomer(@PathVariable("idCustomer") Long idCustomer){
        return waitingListService.getWaitingListByIdCustomer(idCustomer);
    }

    /**
     * Supprime la réservation de la liste d'attente
     * @param idWaitingList
     */
    @GetMapping(value = "waitingList/delete/{idWaitingList}")
    public void deleteWaitingList(@PathVariable("idWaitingList") Long idWaitingList){
        waitingListService.deleteWaitingList(idWaitingList);
    }

    /**
     * Récupération d'une liste d'attente avec la position selon l'id du livre
     * @param idCustomer
     * @return
     */
    @GetMapping(value = "waitingList/getInWaitingList")
    public List<WaitingListDto> returnWaitingListWithPosition(@RequestParam(name = "idCustomer")Long idCustomer){
        List<WaitingListDto> waitingListDto = waitingListService.getWaitingListByIdCustomer(idCustomer);
        int numberInWaitingList;
        List<WaitingListDto> newList = null;

        for (WaitingListDto listDto : waitingListDto
             ) {
            numberInWaitingList = waitingListService.getInWaitingList(listDto);
            EmpruntDto empruntDto = empruntService.getNextReturn(listDto.getBook().getId());
            Date dateReturn = null;

            if (empruntDto != null){
                dateReturn = empruntDto.getReturnDate();
            }

            waitingListService.updateWaintingList
                    (numberInWaitingList, dateReturn, listDto);

        }

        return waitingListDto;
    }
}
