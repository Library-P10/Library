package com.api.library.service.contract;

import com.api.library.dto.WaitingListDto;
import com.api.library.model.WaitingList;

import java.util.List;

public interface WaitingListService {

    /**
     * Récupère la liste d'attente d'un book
     * @param idBook
     * @return
     */
    List<WaitingListDto> getWaitingListByIdBook(Long idBook);

    /**
     * Récupère le nombre d'occurence d'un libre dans une liste d'attente
     * @param idBook
     * @return
     */
    Integer getNumberBookInWaitingList(Long idBook);

    /**
     * Supprime la réservation dans la liste d'attente (WaitingList)
     * @param idWaitingList
     */
    void deleteWaitingList (Long idWaitingList);

    /**
     * Envoie un mail au prochain customer de la liste
     * @param idBook
     */
    void sendMailForNextCustomer(Long idBook);

    /**
     * Récupère la liste d'attente par son id
     * @param idWaiting
     * @return
     */
    WaitingListDto getWaitingListById(Long idWaiting);

    /**
     * getWaitingListByDateRecoveryLimitExceeded
     * @return
     */
    List<WaitingListDto> getWaitingListByDateRecoveryLimitExceeded();

    /**
     * Ajout possible en liste d'attente ou non
     * @param numberBookInWaitingList
     * @param numberOfWaitinListAvailable
     * @param idBook
     * @param idCustomer
     * @return
     */
    Boolean insertInWaitingListAvailable(int numberBookInWaitingList, int numberOfWaitinListAvailable,
                                         Long idBook, Long idCustomer);

    /**
     * Retourne la liste d'attente selon l'id du book et de l'utilisateur
     * @param idCustomer
     * @param idBook
     * @return
     */
    WaitingListDto getWaitingListByIdCustomerAndIdBook(Long idCustomer, Long idBook);

    /**
     * Ajout d'une réservation dans la liste d'attente
     * @param idBook
     * @param idCustomer
     * @return
     */
    WaitingListDto insertWaitingList(Long idBook, Long idCustomer);

    /**
     * Récupère le nombre de personne ayant réservée le livre
     * @param idBook
     * @return
     */
    int getNumberCustomerInWaitingList(Long idBook);

    /**
     * Récupération de la liste d'attente de l'utilisateur
     * @param idCustomer
     * @return
     */
    List<WaitingListDto> getWaitingListByIdCustomer(Long idCustomer);
}
