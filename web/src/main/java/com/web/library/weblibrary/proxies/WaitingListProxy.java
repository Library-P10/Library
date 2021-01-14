package com.web.library.weblibrary.proxies;

import com.web.library.weblibrary.beans.WaitingList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface WaitingListProxy {

    @GetMapping(value = "/waitingList/available")
    Boolean insertAvailable (@RequestParam("idBook") Long idBook,
                             @RequestParam("idCustomer") Long idCustomer);

    /**
     * Ajout d'une réservation en attente
     * @param idCustomer
     * @param idBook
     * @return
     */
    @PostMapping(value = "/waitingList/insert/{idBook}")
    WaitingList insertWaitingList (@RequestParam("idCustomer") Long idCustomer,
                                                  @PathVariable("idBook") Long idBook);

    /**
     * Retourne le nombre de personne ayant réservée(s) le livre
     * @param idBook
     * @return
     */
    @GetMapping(value = "/waitingList/numberCustomer/{idBook}")
    int getNumberCustomerInWaitingList(@PathVariable("idBook") Long idBook);

    /**
     * Récupération de la liste d'attente par utilisateur
     * @param idCustomer
     * @return
     */
    @GetMapping(value = "/waitingList/customer/{idSession}")
    List<WaitingList> getWaitingListByCustomer(@PathVariable("idSession") Long idCustomer);

    /**
     * Supprime la réservation de la liste d'attente
     * @param idWaitingList
     */
    @GetMapping(value = "/waitingList/delete/{idWaitingList}")
    void deleteWaitingList(@PathVariable("idWaitingList") Long idWaitingList);

    /**
     * Récupère une liste d'attente avec la position de l'utilisateur et la date de retour prochain
     * @param idCustomer
     * @return
     */
    @GetMapping(value = "/waitingList/getInWaitingList")
    List<WaitingList> getNumberInWaitingList(@RequestParam(name = "idCustomer") Long idCustomer);
}
