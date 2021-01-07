package com.api.library.service.impl;

import com.api.library.dto.WaitingListDto;
import com.api.library.mapper.WaitingListMapper;
import com.api.library.model.WaitingList;
import com.api.library.repository.BookRepository;
import com.api.library.repository.WaitingListRepository;
import com.api.library.service.contract.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListServiceImpl implements WaitingListService {

    // ----------------- Injections des dépendances ----------------- //

    private final WaitingListRepository waitingListRepository;
    private final BookRepository bookRepository;

    @Autowired
    public WaitingListServiceImpl(WaitingListRepository waitingListRepository, BookRepository bookRepository){
        this.waitingListRepository = waitingListRepository;
        this.bookRepository = bookRepository;
    }

    // -----------------------------------------------------  //

    @Override
    public List<WaitingListDto> getWaitingListByIdBook(final Long idBook) {
        return WaitingListMapper.
                INSTANCE.map(waitingListRepository.getWaitingListByIdBook(idBook));
    }

    @Override
    public Integer getNumberBookInWaitingList(final Long idBook) {
        return waitingListRepository.getNumberBookInWaitingList(idBook);
    }

    /**
     * Supprime la réservation de la liste d'attente
     * @param idWaitingList
     */
    @Override
    public void deleteWaitingList(final Long idWaitingList) {
        WaitingList waitingList = waitingListRepository.getWaitingListById(idWaitingList);
        waitingListRepository.delete(waitingList);
    }

    /**
     * Envoi un mail au prochain sur la liste d'attente
     * @param idBook
     */
    @Override
    public void sendMailForNextCustomer(final Long idBook) {

    }

    /**
     * Récupère la waitingList par son id
     * @param idWaiting
     * @return
     */
    @Override
    public WaitingListDto getWaitingListById(final Long idWaiting) {
        WaitingList waitingList = waitingListRepository.getWaitingListById(idWaiting);
        return WaitingListMapper.INSTANCE.waitingListToWaitingListDto(waitingList);
    }

}
