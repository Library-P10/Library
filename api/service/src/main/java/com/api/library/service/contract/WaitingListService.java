package com.api.library.service.contract;

import com.api.library.dto.WaitingListDto;

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
}
