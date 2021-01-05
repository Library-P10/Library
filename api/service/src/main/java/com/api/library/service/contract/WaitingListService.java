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
}
